package com.denisrebrof.posts.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisrebrof.posts.domain.model.IPostsRepository
import com.denisrebrof.posts.presentation.ui.model.PostViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: IPostsRepository
) : ViewModel() {

    private val postsLiveData = MutableLiveData<List<PostViewState>>(listOf())

    fun getPosts(): LiveData<List<PostViewState>> = postsLiveData

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPosts()
                .map { post ->
                    PostViewState(
                        post.title,
                        post.body
                    )
                }.let(postsLiveData::postValue)
        }
    }

}