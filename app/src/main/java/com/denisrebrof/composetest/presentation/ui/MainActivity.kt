package com.denisrebrof.composetest.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.denisrebrof.composetest.domain.model.SearchResult
import com.denisrebrof.composetest.presentation.ui.model.SearchIntent
import com.denisrebrof.composetest.presentation.ui.model.SearchViewState
import com.denisrebrof.composetest.presentation.ui.theme.ComposeTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Draw()
        }
    }

    @Composable
    fun Draw() {
        ComposeTestTheme {
            Surface(color = MaterialTheme.colors.background) {
                Container()
            }
        }
    }

    @Composable
    fun Container() {
        Column(modifier = Modifier.padding(8f.dp)) {
            Text(text = "Hello!", style = MaterialTheme.typography.subtitle1)
            Text(text = "++++|----", style = MaterialTheme.typography.caption)
            Spacer(modifier = Modifier.height(8f.dp))

            val resultsState: SearchViewState by searchViewModel
                .getViewState()
                .observeAsState(SearchViewState.Undefined)

            SearchResultsBlock(resultsState)

            Spacer(modifier = Modifier.height(8f.dp))

            var searchText: String by rememberSaveable { mutableStateOf("") }
            SearchField(searchText) { update -> searchText = update }

            Spacer(modifier = Modifier.height(8f.dp))

            Button(onClick = { launchSearch(searchText) }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
                Spacer(modifier = Modifier.width(8f.dp))
                Text(text = "Find Matches".uppercase())
            }
        }
    }

    private fun launchSearch(text: String) {
        SearchIntent.StartSearch(text).let(searchViewModel::executeIntent)
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
            is SearchResult.Success -> SearchCompletedBlock(result.count)
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
    fun SearchCompletedBlock(resultsCount: Int) {
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

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Draw()
    }
}