package com.denisrebrof.search.presentation.model

import com.denisrebrof.search.domain.SearchResult

sealed class SearchViewState {
    object Undefined : SearchViewState()
    object Searching : SearchViewState()
    data class SearchCompleted(val result: SearchResult) : SearchViewState()
}
