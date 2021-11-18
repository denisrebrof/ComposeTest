package com.denisrebrof.search.presentation.model

sealed class SearchIntent {
    data class StartSearch(val text: String): SearchIntent()
}
