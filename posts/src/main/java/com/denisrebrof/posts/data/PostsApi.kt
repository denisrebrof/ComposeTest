package com.denisrebrof.posts.data

import com.denisrebrof.posts.data.model.PostModel
import com.denisrebrof.posts.domain.model.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface PostsApi {
    @GET("posts")
    suspend fun getPosts(): List<PostModel>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId: Long): Post
}