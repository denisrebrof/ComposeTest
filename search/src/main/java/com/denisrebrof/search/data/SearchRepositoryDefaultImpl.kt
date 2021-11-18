package com.denisrebrof.search.data

import com.denisrebrof.search.data.model.JsonPlaceholderPost
import com.denisrebrof.search.domain.ISearchRepository
import javax.inject.Inject

class SearchRepositoryDefaultImpl @Inject constructor(
    private val placeholderApi: JsonPlaceholderApi
) : ISearchRepository {

    override suspend fun search(text: String): com.denisrebrof.search.domain.SearchResult {
        return kotlin.runCatching {
            placeholderApi.getPosts().countEntries(text).let(com.denisrebrof.search.domain.SearchResult::Success)
        }.getOrDefault(com.denisrebrof.search.domain.SearchResult.Error)
    }

    private fun List<JsonPlaceholderPost>.countEntries(text: String): Int = sumOf { post ->
        post.body.findEntriesCount(text)
    }

    private fun String.findEntriesCount(source: String) = split(source).count().minus(1).coerceAtLeast(0)
}