package com.denisrebrof.composetest.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.denisrebrof.posts.presentation.postlist.PostActivity
import com.denisrebrof.theming.presentation.theme.DGVGTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Draw()
        }
    }

    @Composable
    fun Draw() {
        DGVGTheme {
            MainActivityView { postId ->
                PostActivity.getIntent(this, postId).let(this::startActivity)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DrawPreview() {
        DGVGTheme {
            MainActivityViewPreview()
        }
    }
}