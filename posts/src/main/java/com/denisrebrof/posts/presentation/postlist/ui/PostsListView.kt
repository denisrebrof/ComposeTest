package com.denisrebrof.posts.presentation.postlist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.denisrebrof.posts.domain.model.Post
import com.denisrebrof.posts.presentation.postlist.PostsViewModel
import com.denisrebrof.theming.presentation.theme.DGVGTheme
import com.denisrebrof.utils.toLazyPagingItems

@Preview(showBackground = true)
@Composable
fun PostsListViewPreview() = DGVGTheme(darkTheme = true) {
    PostsListUI(pagingItems = listOf<Post>().toLazyPagingItems())
}

@Composable
fun PostsListView(postsViewModel: PostsViewModel, clickedPostId: (Long) -> Unit = {}) {
    val postsListDelegate = postsViewModel.pagedPosts.collectAsLazyPagingItems()
    PostsListUI(postsListDelegate, clickedPostId)
}

@Composable
fun PostsListUI(
    pagingItems: LazyPagingItems<Post> = listOf<Post>().toLazyPagingItems(),
    clickedPostId: (Long) -> Unit = {}
) = LazyColumn(contentPadding = PaddingValues(8.dp, 8.dp)) {
    items(pagingItems) { postViewState ->
        postViewState?.let { post ->
            PostItemView(post) {
                clickedPostId(post.id)
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
    pagingItems.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                item {
                    Text(modifier = Modifier.fillParentMaxSize(), text = "Loading...")
                }
            }
            loadState.append is LoadState.Loading -> {
                item {
                    Text(text = "Loading")
                }
            }
            loadState.refresh is LoadState.Error -> {
                val e = loadState.refresh as LoadState.Error
                item {
                    Text(
                        text = e.error.localizedMessage!!,
                        modifier = Modifier
                            .fillParentMaxSize()
                            .clickable { retry() }
                    )
                }
            }
            loadState.append is LoadState.Error -> {
                val e = loadState.append as LoadState.Error
                item {
                    Text(
                        text = e.error.localizedMessage!!,
                        modifier = Modifier.clickable { retry() }
                    )
                }
            }
        }
    }
}