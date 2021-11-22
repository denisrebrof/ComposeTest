package com.denisrebrof.theming.presentation.views

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun DefaultAppBar(content: @Composable (RowScope.() -> Unit)) {
    TopAppBar(
        elevation = 8.dp,
        contentPadding = PaddingValues(16.dp, 0.dp),
        content = content
    )
}