package com.denisrebrof.posts.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.denisrebrof.posts.presentation.ui.model.PostViewState

@Preview
@Composable
fun PostViewPreview() = PostView()

@Composable
fun PostView(viewState: PostViewState = PostViewState("default", "default")) {
    Column {
        Text(text = viewState.title, style = MaterialTheme.typography.h6)
        Text(text = viewState.body, style = MaterialTheme.typography.body2)
    }
}