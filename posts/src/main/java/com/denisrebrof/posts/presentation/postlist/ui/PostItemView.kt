package com.denisrebrof.posts.presentation.postlist.ui

import androidx.compose.foundation.clickable
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
import com.denisrebrof.posts.domain.model.Post
import com.denisrebrof.theming.presentation.theme.DGVGTheme

@Preview
@Composable
fun PostItemViewPreview() {
    DGVGTheme(darkTheme = true) {
        PostItemView()
    }
}

@Composable
fun PostItemView(
    post: Post = Post(0L, "default", "default"),
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = 8.dp
    ) {
        Column {
            Text(
                text = post.title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = post.body,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(8.dp)
            )
        }
    }

}