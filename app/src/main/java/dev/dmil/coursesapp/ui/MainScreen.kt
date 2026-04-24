package dev.dmil.coursesapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.dmil.coursesapp.R
import dev.dmil.coursesapp.core.ui.theme.Green
import dev.dmil.coursesapp.core.ui.theme.White
import dev.dmil.coursesapp.feature.account.ui.AccountScreen
import dev.dmil.coursesapp.feature.favorites.ui.FavouritesScreen
import dev.dmil.coursesapp.feature.home.ui.HomeScreen
import dev.dmil.coursesapp.navigation.Screen

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val currentDestination by navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = currentDestination?.destination?.route == Screen.Home.route,
                    onClick = { navController.navigateToTab(Screen.Home.route) },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.home),
                            contentDescription = "Home Icon",
                            tint = if (currentDestination?.destination?.route == Screen.Home.route) Green else White
                        )
                    },
                    label = {
                        Text(
                            text = Screen.Home.label,
                            color = if (currentDestination?.destination?.route == Screen.Home.route) Green else White
                        )
                    }
                )
                NavigationBarItem(
                    selected = currentDestination?.destination?.route == Screen.Favourites.route,
                    onClick = { navController.navigateToTab(Screen.Favourites.route) },
                    icon = {
                        Icon(
                            painter = painterResource(dev.dmil.coursesapp.core.R.drawable.favourites),
                            contentDescription = "Favourites Icon",
                            tint = if (currentDestination?.destination?.route == Screen.Favourites.route) Green else White
                        )
                    },
                    label = {
                        Text(
                            text = Screen.Favourites.label,
                            color = if (currentDestination?.destination?.route == Screen.Favourites.route) Green else White
                        )
                    }
                )
                NavigationBarItem(
                    selected = currentDestination?.destination?.route == Screen.Account.route,
                    onClick = { navController.navigateToTab(Screen.Account.route) },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.account),
                            contentDescription = "Account Icon",
                            tint = if (currentDestination?.destination?.route == Screen.Account.route) Green else White
                        )
                    },
                    label = {
                        Text(
                            text = Screen.Account.label,
                            color = if (currentDestination?.destination?.route == Screen.Account.route) Green else White
                        )
                    }
                )
            }
        }
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen()
            }
            composable(Screen.Favourites.route) {
                FavouritesScreen()
            }
            composable(Screen.Account.route) {
                AccountScreen()
            }
        }
    }

}

fun NavController.navigateToTab(route: String) {
    navigate(route) {
        popUpTo(graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}