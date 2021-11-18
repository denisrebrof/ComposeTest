package com.denisrebrof.search.domain

sealed class SearchResult {
    object Error : SearchResult()
    data class Success(val count: Int = 0) : SearchResult()
}
