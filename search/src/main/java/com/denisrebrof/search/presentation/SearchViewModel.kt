package com.denisrebrof.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisrebrof.search.domain.ISearchRepository
import com.denisrebrof.search.presentation.model.SearchIntent
import com.denisrebrof.search.presentation.model.SearchViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: ISearchRepository
) : ViewModel() {

    private val searchStateLiveData = MutableLiveData<SearchViewState>(SearchViewState.Undefined)

    fun getViewState(): LiveData<SearchViewState> = searchStateLiveData

    fun executeIntent(intent: SearchIntent) {
        when (intent) {
            is SearchIntent.StartSearch -> startSearch(intent.text)
        }
    }

    private fun startSearch(searchPattern: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchStateLiveData.postValue(SearchViewState.Searching)
            repository.search(searchPattern).let(SearchViewState::SearchCompleted).let(searchStateLiveData::postValue)
        }
    }
}