package dev.dmil.coursesapp.navigation

sealed class Screen(val route: String, val label: String) {
    object Login: Screen("login", "")
    object Main: Screen("main", "")
    object Home: Screen("home", "Главная")
    object Favourites: Screen("favourites", "Избранное")
    object Account: Screen("account", "Аккаунт")
}