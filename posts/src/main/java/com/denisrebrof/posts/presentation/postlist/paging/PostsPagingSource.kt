package com.denisrebrof.posts.presentation.postlist.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.denisrebrof.posts.domain.IPostsRepository
import com.denisrebrof.posts.domain.model.Post
import javax.inject.Inject

class PostsPagingSource @Inject constructor(
    private val postsRepository: IPostsRepository
) : PagingSource<Int, Post>() {

    override suspend fun load(params: LoadParams<Int>) = kotlin.runCatching {
        val nextPage = params.key ?: 1
        val movieListResponse = postsRepository.getPostsPaged(nextPage, params.loadSize)

        LoadResult.Page(
            data = movieListResponse.posts,
            prevKey = if (nextPage > 1) nextPage.minus(1) else null,
            nextKey = movieListResponse.pageNumber.plus(1)
        )
    }.getOrElse { e ->
        LoadResult.Error(e)
    }

    override fun getRefreshKey(state: PagingState<Int, Post>) = state.anchorPosition
}