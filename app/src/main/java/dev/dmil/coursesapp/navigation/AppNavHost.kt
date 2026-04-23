package dev.dmil.coursesapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.dmil.coursesapp.feature.login.ui.LoginScreen
import dev.dmil.coursesapp.ui.MainScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Main.route)
                }
            )
        }

        composable(Screen.Main.route) {
            MainScreen()
        }

    }

}