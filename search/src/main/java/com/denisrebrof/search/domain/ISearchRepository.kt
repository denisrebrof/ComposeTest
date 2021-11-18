package com.denisrebrof.search.domain

interface ISearchRepository {
    suspend fun search(text: String): SearchResult
}