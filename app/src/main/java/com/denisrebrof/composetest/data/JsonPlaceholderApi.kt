package com.denisrebrof.composetest.data

import com.denisrebrof.composetest.data.model.JsonPlaceholderPost
import retrofit2.http.GET

interface JsonPlaceholderApi {
    @GET("posts")
    suspend fun getPosts(): List<JsonPlaceholderPost>
}