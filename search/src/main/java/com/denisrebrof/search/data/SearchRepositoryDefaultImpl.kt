package com.denisrebrof.search.data

import com.denisrebrof.posts.data.model.PostModel
import com.denisrebrof.posts.data.PostsApi
import com.denisrebrof.search.domain.ISearchRepository
import javax.inject.Inject

class SearchRepositoryDefaultImpl @Inject constructor(
    private val placeholderApi: PostsApi
) : ISearchRepository {

    override suspend fun search(text: String): com.denisrebrof.search.domain.SearchResult {
        return kotlin.runCatching {
            placeholderApi.getPosts().countEntries(text).let(com.denisrebrof.search.domain.SearchResult::Success)
        }.getOrDefault(com.denisrebrof.search.domain.SearchResult.Error)
    }

    private fun List<PostModel>.countEntries(text: String): Int = sumOf { post ->
        post.body.findEntriesCount(text)
    }

    private fun String.findEntriesCount(source: String) = split(source).count().minus(1).coerceAtLeast(0)
}