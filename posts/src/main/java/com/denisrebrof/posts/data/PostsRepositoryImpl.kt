package com.denisrebrof.posts.data

import com.denisrebrof.posts.data.model.PostModel
import com.denisrebrof.posts.domain.IPostsRepository
import com.denisrebrof.posts.domain.model.Post
import com.denisrebrof.posts.domain.model.PostRequestResult
import com.denisrebrof.posts.presentation.postlist.model.PostsPage
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsApi: PostsApi
) : IPostsRepository {

    override suspend fun getPosts(): List<Post> = postsApi.getPosts().map(PostModel::toPost)

    override suspend fun getPost(id: Long): PostRequestResult = kotlin.runCatching {
        postsApi.getPost(id).let(PostRequestResult::Success)
    }.getOrElse {
        PostRequestResult.Error
    }

    override suspend fun getPostsPaged(page: Int, pageSize: Int): PostsPage {
        val id = (page - 1).coerceAtLeast(0) * pageSize
        return postsApi.getPosts().subList(id, id + pageSize).let { postModels ->
            val posts = postModels.map(PostModel::toPost)
            PostsPage(posts, pageNumber = page)
        }
    }
}