package dev.dmil.coursesapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import dev.dmil.coursesapp.core.ui.theme.Black
import dev.dmil.coursesapp.core.ui.theme.DarkGrey
import dev.dmil.coursesapp.core.ui.theme.Green
import dev.dmil.coursesapp.core.ui.theme.White

private val DarkColorScheme = darkColorScheme(
    primary = Green,
    background = Black,
    surface = DarkGrey,
    onPrimary = White,
    onBackground = White,
    onSurface = White
)

@Composable
fun CoursesAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}