package com.denisrebrof.composetest.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.denisrebrof.composetest.presentation.theme.ComposeTestTheme
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
        ComposeTestTheme {
            MainActivityView()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DrawPreview() {
        ComposeTestTheme {
            MainActivityViewPreview()
        }
    }
}