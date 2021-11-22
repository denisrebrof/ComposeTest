package com.denisrebrof.posts.presentation.postlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.denisrebrof.posts.domain.IPostsRepository
import com.denisrebrof.posts.domain.model.Post
import com.denisrebrof.posts.presentation.postlist.paging.PostsPagingSourceFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val repository: IPostsRepository,
    private val sourceFactory: PostsPagingSourceFactory
) : ViewModel() {

    private val config = PagingConfig(pageSize = 5)
    private val postsLiveData = MutableLiveData<List<Post>>(listOf())

    val posts: LiveData<List<Post>> = postsLiveData

    val pagedPosts = Pager(
        config,
        pagingSourceFactory = sourceFactory::create
    ).flow

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPosts().let(postsLiveData::postValue)
        }
    }
}