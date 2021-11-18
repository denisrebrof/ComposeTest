package com.denisrebrof.search.data

import com.denisrebrof.search.data.model.JsonPlaceholderPost
import retrofit2.http.GET

interface JsonPlaceholderApi {
    @GET("posts")
    suspend fun getPosts(): List<JsonPlaceholderPost>
}