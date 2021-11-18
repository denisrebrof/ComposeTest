package com.denisrebrof.composetest.presentation.ui.model

import com.denisrebrof.composetest.domain.model.SearchResult

sealed class SearchViewState {
    object Undefined : SearchViewState()
    object Searching : SearchViewState()
    data class SearchCompleted(val result: SearchResult) : SearchViewState()
}
