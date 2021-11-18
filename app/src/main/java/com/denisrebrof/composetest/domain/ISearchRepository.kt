package com.denisrebrof.composetest.domain

import com.denisrebrof.composetest.domain.model.SearchResult

interface ISearchRepository {
    suspend fun search(text: String): SearchResult
}