package com.denisrebrof.posts.presentation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.denisrebrof.posts.domain.model.Post

@Composable
fun PostsViewPreview() = PostsUI()

@Composable
fun PostsView(postsViewModel: PostsViewModel) {
    val postsListDelegate = postsViewModel.getPosts().observeAsState(listOf())
    PostsUI(postsListDelegate) {
        postsViewModel.refresh()
    }
}

@Composable
fun PostsUI(
    postsStateDelegate: State<List<Post>> = derivedStateOf { listOf() },
    onRefresh: @Composable () -> Unit = {}
) {
    val posts: List<Post> by postsStateDelegate
    LazyColumn {
//        posts.forEach()
    }

}