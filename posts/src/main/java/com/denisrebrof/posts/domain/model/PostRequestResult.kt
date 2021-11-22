package com.denisrebrof.posts.domain.model

sealed class PostRequestResult {
    data class Success(val post: Post): PostRequestResult()
    object PostNotFound: PostRequestResult()
    object Error: PostRequestResult()
}
