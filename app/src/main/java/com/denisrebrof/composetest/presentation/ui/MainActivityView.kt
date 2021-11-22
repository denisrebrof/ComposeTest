package com.denisrebrof.composetest.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.denisrebrof.posts.presentation.postlist.ui.PostsListView
import com.denisrebrof.posts.presentation.postlist.ui.PostsListViewPreview
import com.denisrebrof.theming.presentation.theme.DGVGTheme
import com.denisrebrof.theming.presentation.views.DefaultAppBar

@Preview
@Composable
fun MainActivityViewPreview() = DGVGTheme(darkTheme = true) {
    MainActivityUI {
        PostsListViewPreview()
    }
}

@Composable
fun MainActivityView(
    postClick: (Long) -> Unit = {}
) {
    MainActivityUI {
        PostsListView(postClick)
    }
}

@Composable
fun MainActivityUI(
    content: @Composable () -> Unit = {}
) = Scaffold(
    topBar = {
        DefaultAppBar {
            Text(text = "Pages", style = MaterialTheme.typography.h6)
        }
    },
    backgroundColor = MaterialTheme.colors.background,
) {
    Column(modifier = Modifier.padding(8f.dp)) {
        content()
    }
}