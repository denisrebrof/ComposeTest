package com.denisrebrof.posts.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.denisrebrof.posts.presentation.ui.model.PostViewState

@Preview
@Composable
fun PostViewPreview() = PostView()

@Composable
fun PostView(viewState: PostViewState = PostViewState("default", "default")) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            Text(text = viewState.title, style = MaterialTheme.typography.h6, modifier = Modifier.padding(8.dp))
            Text(text = viewState.body, style = MaterialTheme.typography.body2, modifier = Modifier.padding(8.dp))
        }
    }

}