package com.denisrebrof.posts.data

import com.denisrebrof.posts.data.model.PostModel
import retrofit2.http.GET

interface PostsApi {
    @GET("posts")
    suspend fun getPosts(): List<PostModel>
}