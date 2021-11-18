package com.denisrebrof.composetest.domain.model

sealed class SearchResult {
    object Error : SearchResult()
    data class Success(val count: Int = 0) : SearchResult()
}
