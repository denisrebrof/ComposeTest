package com.denisrebrof.posts.data

import com.denisrebrof.posts.domain.model.IPostsRepository
import com.denisrebrof.posts.domain.model.Post
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsApi: PostsApi
) : IPostsRepository {

    override suspend fun getPosts(): List<Post> {
        return postsApi.getPosts().map { model ->
            Post(
                model.id,
                model.title,
                model.body
            )
        }
    }
}