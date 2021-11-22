package com.denisrebrof.posts.data.model

import com.denisrebrof.posts.domain.model.Post
import com.google.gson.annotations.SerializedName

data class PostModel(
    @SerializedName("userId")
    val userId: Int,
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
) {
    fun toPost() = Post(
        id = id,
        title = title,
        body = body
    )
}