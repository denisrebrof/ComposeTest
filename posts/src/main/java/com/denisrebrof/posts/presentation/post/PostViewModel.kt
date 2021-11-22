package com.denisrebrof.posts.presentation.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.denisrebrof.posts.domain.IPostsRepository
import com.denisrebrof.posts.domain.model.PostRequestResult
import com.denisrebrof.posts.presentation.post.model.PostIntent
import com.denisrebrof.posts.presentation.post.model.PostIntent.LoadPost
import com.denisrebrof.posts.presentation.post.model.PostIntent.Refresh
import com.denisrebrof.posts.presentation.post.model.PostLoadingViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import com.denisrebrof.posts.presentation.post.model.PostViewState as ViewState

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postsRepository: IPostsRepository
) : ViewModel() {

    companion object {
        val defaultViewState = ViewState(PostLoadingViewState.Idle, false)
    }

    private var postId = Optional.empty<Long>()
    private val state = MutableLiveData(defaultViewState)

    val viewState: LiveData<ViewState> = state

    fun execute(intent: PostIntent) = when (intent) {
        is LoadPost -> loadPost(intent.postId)
        Refresh -> postId.ifPresent(this::loadPost)
    }

    private fun loadPost(postID: Long) {
        postId = Optional.of(postID)
        ViewState(PostLoadingViewState.Loading, false).let(state::setValue)
        viewModelScope.launch {
            postsRepository.getPost(postID).toPostLoadingViewState().let(state::postValue)
        }
    }

    private fun PostRequestResult.toPostLoadingViewState(): ViewState {
        val loadingViewState = when (this) {
            PostRequestResult.Error -> PostLoadingViewState.Error
            PostRequestResult.PostNotFound -> PostLoadingViewState.Error
            is PostRequestResult.Success -> PostLoadingViewState.Loaded(post)
        }
        return ViewState(loadingViewState, true)
    }
}