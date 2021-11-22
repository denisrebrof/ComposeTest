package com.denisrebrof.theming.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = GreenLight,
    primaryVariant = GreenLight,
    secondary = OrangeLight,
    background = TintedBlack,
    surface = TintedBlack,
    onPrimary = TintedBlack,
    onSecondary = TintedWhite,
    onBackground = TintedWhite,
    onSurface = TintedWhite,
)

private val LightColorPalette = lightColors(
    primary = Green,
    primaryVariant = Green,
    secondary = Orange,
    background = TintedWhite,
    surface = TintedWhite,
    onPrimary = TintedWhite,
    onSecondary = TintedBlack,
    onBackground = TintedBlack,
    onSurface = TintedBlack,
)

@Composable
fun DGVGTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}