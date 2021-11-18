package com.denisrebrof.search.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.denisrebrof.search.domain.SearchResult
import com.denisrebrof.search.presentation.SearchViewModel
import com.denisrebrof.search.presentation.model.SearchIntent
import com.denisrebrof.search.presentation.model.SearchViewState

@Preview
@Composable
fun SearchViewPreview() = SearchUI()

@Composable
fun SearchView(searchViewModel: SearchViewModel) {
    val viewStateDelegate = searchViewModel.getViewState().observeAsState(SearchViewState.Undefined)
    SearchUI(viewStateDelegate) { searchText ->
        SearchIntent.StartSearch(searchText).let(searchViewModel::executeIntent)
    }
}

@Composable
fun SearchUI(
    viewStateDelegate: State<SearchViewState> = derivedStateOf { SearchViewState.Undefined },
    searchClick: (String) -> Unit = {}
) {
    val viewState: SearchViewState by viewStateDelegate
    var searchText: String by rememberSaveable { mutableStateOf("") }
    Column(modifier = Modifier.padding(8f.dp)) {
        SearchResultsBlock(viewState)
        Spacer(modifier = Modifier.height(8f.dp))
        SearchField(searchText) { update -> searchText = update }
        Spacer(modifier = Modifier.height(8f.dp))
        Button(onClick = { searchClick(searchText) }) {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
            Spacer(modifier = Modifier.width(8f.dp))
            Text(text = "Find Matches".uppercase())
        }
    }
}

@Composable
fun SearchResultsBlock(viewState: SearchViewState) {
    when (viewState) {
        SearchViewState.Undefined -> return
        SearchViewState.Searching -> SearchLoadingBlock()
        is SearchViewState.SearchCompleted -> SearchCompletedBlock(viewState.result)
    }
}

@Composable
fun SearchLoadingBlock() {
    Card(elevation = 8f.dp) {
        Text(
            modifier = Modifier.padding(
                horizontal = 8f.dp,
                vertical = 8f.dp
            ),
            text = "Loading ...",
            style = MaterialTheme.typography.subtitle2,
        )
    }
}

@Composable
fun SearchCompletedBlock(result: SearchResult) {
    when (result) {
        SearchResult.Error -> SearchErrorBlock()
        is SearchResult.Success -> SearchSucceedBlock(result.count)
    }
}

@Composable
fun SearchErrorBlock() {
    Card(
        elevation = 8f.dp,
        backgroundColor = MaterialTheme.colors.error
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = 8f.dp,
                vertical = 8f.dp
            ),
            text = "Error while searching",
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onError
        )
    }
}

@Composable
fun SearchSucceedBlock(resultsCount: Int) {
    Card(elevation = 8f.dp) {
        Text(
            modifier = Modifier.padding(
                horizontal = 8f.dp,
                vertical = 8f.dp
            ),
            text = "Found matches: $resultsCount",
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
fun SearchField(text: String, onTextChange: (String) -> Unit) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text("Search") }
    )
}