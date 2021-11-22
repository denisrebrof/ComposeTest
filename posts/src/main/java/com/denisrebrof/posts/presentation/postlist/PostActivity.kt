package com.denisrebrof.posts.presentation.postlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.denisrebrof.posts.presentation.post.PostViewModel
import com.denisrebrof.posts.presentation.post.model.PostIntent
import com.denisrebrof.posts.presentation.post.ui.PostView
import com.denisrebrof.posts.presentation.post.ui.PostViewPreview
import com.denisrebrof.theming.presentation.theme.DGVGTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PostActivity : AppCompatActivity() {

    private val viewModel: PostViewModel by viewModels()

    companion object {
        private const val POST_ID = "POST_ID"
        fun getIntent(context: Context, postID: Long): Intent {
            return Intent(context, PostActivity::class.java).apply {
                putExtra(POST_ID, postID)
            }
        }
    }

    private val postId: Optional<Long> by lazy {
        Optional.ofNullable(intent.extras?.getLong(POST_ID))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Draw()
        }
        postId.ifPresent { postId ->
            PostIntent.LoadPost(postId).let(viewModel::execute)
        }
    }

    @Composable
    fun Draw() {
        DGVGTheme {
            PostView(hiltViewModel())
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DrawPreview() {
        DGVGTheme {
            PostViewPreview()
        }
    }
}