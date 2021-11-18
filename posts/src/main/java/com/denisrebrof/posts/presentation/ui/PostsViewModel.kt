package com.denisrebrof.posts.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisrebrof.posts.domain.model.IPostsRepository
import com.denisrebrof.posts.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: IPostsRepository
) : ViewModel() {

    private val searchStateLiveData = MutableLiveData<List<Post>>(listOf())

    fun getPosts(): LiveData<List<Post>> = searchStateLiveData

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPosts().let(searchStateLiveData::postValue)
        }
    }

}