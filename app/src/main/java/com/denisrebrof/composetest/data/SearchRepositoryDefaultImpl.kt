package com.denisrebrof.composetest.data

import com.denisrebrof.composetest.data.model.JsonPlaceholderPost
import com.denisrebrof.composetest.domain.ISearchRepository
import com.denisrebrof.composetest.domain.model.SearchResult
import javax.inject.Inject

class SearchRepositoryDefaultImpl @Inject constructor(
    private val placeholderApi: JsonPlaceholderApi
) : ISearchRepository {

    override suspend fun search(text: String): SearchResult {
        return kotlin.runCatching {
            placeholderApi.getPosts().countEntries(text).let(SearchResult::Success)
        }.getOrDefault(SearchResult.Error)
    }

    private fun List<JsonPlaceholderPost>.countEntries(text: String): Int = sumOf { post ->
        post.body.findEntriesCount(text)
    }

    private fun String.findEntriesCount(source: String) = split(source).count().minus(1).coerceAtLeast(0)
}