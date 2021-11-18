package com.denisrebrof.composetest.presentation.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun CanvasViewPreview() {
    CanvasUI()
}

@Composable
fun CanvasView() {
    CanvasUI()
}

@Composable
fun CanvasUI(

) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(
            Color.Blue,
            topLeft = Offset(0f, 0f),
            size = Size(this.size.width, 55f)
        )
    }
}