package com.denisrebrof.posts.presentation.post.model

import com.denisrebrof.posts.domain.model.Post

data class PostViewState(
    val postState: PostLoadingViewState,
    val refreshActive: Boolean
)

sealed class PostLoadingViewState {
    object Idle : PostLoadingViewState()
    object Loading : PostLoadingViewState()
    data class Loaded(val post: Post) : PostLoadingViewState()
    object Error : PostLoadingViewState()
}