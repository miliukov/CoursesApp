package dev.dmil.coursesapp.feature.home.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {
    HomeContent()
}

@Composable
private fun HomeContent() {
    Text(text = "Home Screen")
}