package com.denisrebrof.posts.domain

import com.denisrebrof.posts.domain.model.Post
import com.denisrebrof.posts.domain.model.PostRequestResult
import com.denisrebrof.posts.presentation.postlist.model.PostsPage

interface IPostsRepository {
    suspend fun getPosts(): List<Post>
    suspend fun getPost(id: Long): PostRequestResult
    suspend fun getPostsPaged(page: Int, pageSize: Int): PostsPage
}