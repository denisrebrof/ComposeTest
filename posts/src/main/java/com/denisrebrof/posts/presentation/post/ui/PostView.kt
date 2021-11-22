package com.denisrebrof.posts.presentation.post.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.denisrebrof.posts.domain.model.Post
import com.denisrebrof.posts.presentation.post.PostViewModel
import com.denisrebrof.posts.presentation.post.model.PostIntent
import com.denisrebrof.posts.presentation.post.model.PostLoadingViewState
import com.denisrebrof.posts.presentation.post.model.PostViewState
import com.denisrebrof.theming.presentation.theme.DGVGTheme
import com.denisrebrof.theming.presentation.views.DefaultAppBar
import kotlin.reflect.safeCast

@Preview
@Composable
fun PostViewPreview() = DGVGTheme {
    PostViewUI(
        derivedStateOf {
//        PostViewState(PostLoadingViewState.Idle, false)
            PostViewState(
                postState = PostLoadingViewState.Loaded(
                    post = Post(0, "Ulala", "Lorem impusm dolor sit amet")
                ),
                refreshActive = true
            )
        }
    )
}


@Composable
fun PostView(viewModel: PostViewModel) {
    val viewStateDelegate = viewModel.viewState.observeAsState(PostViewModel.defaultViewState)
    PostViewUI(viewStateDelegate) {
        viewModel.execute(PostIntent.Refresh)
    }
}

@Composable
fun PostViewUI(
    viewStateDelegate: State<PostViewState>,
    refreshClick: () -> Unit = {}
) {
    val viewState: PostViewState by viewStateDelegate
    Scaffold(
        topBar = { DrawAppBar(viewState) },
        backgroundColor = MaterialTheme.colors.background,
        floatingActionButton = {
            if (viewState.refreshActive) {
                RefreshFAB(refreshClick)
            }
        }
    ) {
        val text = getSimpleBodyText(viewState)
        Text(
            modifier = Modifier.padding(8f.dp),
            text = text,
            style = MaterialTheme.typography.body1
        )
    }
}

private fun getSimpleBodyText(
    viewState: PostViewState
) = when (val postState = viewState.postState) {
    PostLoadingViewState.Error -> "Error Occurred"
    PostLoadingViewState.Idle -> String()
    is PostLoadingViewState.Loaded -> postState.post.body
    PostLoadingViewState.Loading -> "Loading..."
}

@Composable
private fun DrawAppBar(viewState: PostViewState) {
    DefaultAppBar {
        val post = viewState.postState.let(PostLoadingViewState.Loaded::class::safeCast)?.post
        val topBarText = post?.title ?: String()
        Text(text = topBarText, style = MaterialTheme.typography.h6)
    }
}

@Composable
private fun RefreshFAB(refreshClick: () -> Unit) {
    FloatingActionButton(onClick = refreshClick) {
        Icon(imageVector = Icons.Filled.Refresh, contentDescription = "refresh")
    }
}