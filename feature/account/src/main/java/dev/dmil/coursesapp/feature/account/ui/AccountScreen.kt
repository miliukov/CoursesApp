package dev.dmil.coursesapp.feature.account.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AccountScreen() {
    AccountContent()
}

@Composable
private fun AccountContent() {
    Text(text = "Account Screen")
}