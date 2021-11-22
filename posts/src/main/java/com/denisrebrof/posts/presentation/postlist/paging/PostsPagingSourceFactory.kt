package com.denisrebrof.posts.presentation.postlist.paging

import com.denisrebrof.posts.domain.IPostsRepository
import javax.inject.Inject

class PostsPagingSourceFactory @Inject constructor(
    private val repository: IPostsRepository
) {
    fun create() = PostsPagingSource(repository)
}