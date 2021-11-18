package com.denisrebrof.composetest.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Preview
@Composable
fun MainActivityViewPreview() {
    MainActivityUI {
        SearchUI()
    }
}

@Composable
fun MainActivityView() {
    MainActivityUI {
        SearchView(hiltViewModel())
    }
}

@Composable
fun MainActivityUI(
    searchView: @Composable () -> Unit
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.padding(8f.dp)) {
            Text(
                modifier = Modifier.padding(8f.dp),
                text = "Hello!",
                style = MaterialTheme.typography.subtitle1
            )
//            Text(text = "++++++*-- 131", style = MaterialTheme.typography.caption)
            Spacer(modifier = Modifier.height(8f.dp))
            searchView()
//            CanvasView()
//            ExpandableBox()

        }
    }
}