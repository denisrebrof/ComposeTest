package com.denisrebrof.composetest.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.denisrebrof.posts.presentation.postlist.ui.PostsListView
import com.denisrebrof.posts.presentation.postlist.PostsViewModel
import com.denisrebrof.posts.presentation.postlist.ui.PostsListViewPreview
import com.denisrebrof.search.presentation.ui.SearchUI
import com.denisrebrof.search.presentation.ui.SearchView
import com.denisrebrof.theming.presentation.theme.DGVGTheme
import com.denisrebrof.theming.presentation.views.DefaultAppBar

@Preview
@Composable
fun MainActivityViewPreview() = DGVGTheme(darkTheme = true) {
    MainActivityUI(
        { SearchUI() },
        { PostsListViewPreview() }
    )
}

@Composable
fun MainActivityView(
    postClick: (Long) -> Unit = {}
) {
    val postsViewModel: PostsViewModel = hiltViewModel()
    MainActivityUI(
        { SearchView(hiltViewModel()) },
        { PostsListView(postsViewModel, postClick) },
        { postsViewModel.refresh() }
    )
}

@Composable
fun MainActivityUI(
    searchView: @Composable () -> Unit,
    postsView: @Composable () -> Unit,
    fabAction: () -> Unit = {}
) = Scaffold(
    topBar = {
        DefaultAppBar {
            Text(text = "Compose Test", style = MaterialTheme.typography.h6)
        }
    },
    backgroundColor = MaterialTheme.colors.background,
    floatingActionButton = {
        FloatingActionButton(onClick = fabAction) {
            Icon(imageVector = Icons.Filled.Refresh, contentDescription = "search")
        }
    }
) {
    Column(modifier = Modifier.padding(8f.dp)) {
        Text(
            modifier = Modifier.padding(8f.dp),
            text = "Hello!",
            style = MaterialTheme.typography.subtitle1
        )
//            Text(text = "++++++*-- 131", style = MaterialTheme.typography.caption)
        Spacer(modifier = Modifier.height(8f.dp))
        searchView()
        val dividerColor = MaterialTheme.colors.onSurface.copy(alpha = 0.32f)
        Divider(color = dividerColor, thickness = 1.dp)
        postsView()
//            CanvasView()
//            ExpandableBox()

    }
}