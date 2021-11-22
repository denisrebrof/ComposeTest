package com.denisrebrof.posts.presentation.postlist.model

import com.denisrebrof.posts.domain.model.Post

data class PostsPage(
    val posts: List<Post>,
    val pageNumber: Int
)