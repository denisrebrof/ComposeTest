package com.denisrebrof.posts.domain.model

interface IPostsRepository {
    suspend fun getPosts(): List<Post>
}