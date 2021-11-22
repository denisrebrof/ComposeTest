package com.denisrebrof.posts.presentation.post.model

sealed class PostIntent {
    data class LoadPost(val postId: Long): PostIntent()
    object Refresh: PostIntent()
}
