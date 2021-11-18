package com.denisrebrof.composetest.presentation.ui.model

sealed class SearchIntent {
    data class StartSearch(val text: String): SearchIntent()
}
