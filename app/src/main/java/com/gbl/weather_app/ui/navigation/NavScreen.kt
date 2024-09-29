package com.gbl.weather_app.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun NavScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { contentPadding ->
        NavHostView(
            navController = navController, modifier = Modifier
                .padding(contentPadding)
        )
    }
}

@Composable
fun NavHostView(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = SearchLocationsScreenRoute,
        modifier = modifier
    ) {
        searchLocationDestination(
            onNavigateToForecastScreen = { locationId ->
                navController.navigateToForecastScreen(locationId)
            }
        )
        savedLocationsDestination(
            onNavigateToForecastScreen = { locationId ->
                navController.navigateToForecastScreen(locationId)
            }
        )
        forecastScreenDestination(
            onNavigateToPreviousScreen = { navController.popBackStack() }
        )
    }
}

@Composable
private fun BottomBar(navController: NavHostController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val topLevelRouteList = getTopLevelRoutes()
        topLevelRouteList.forEach { topLevelRoute ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.hasRoute(topLevelRoute.route::class) } == true,
                onClick = {
                    navController.navigate(topLevelRoute.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = false }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = topLevelRoute.icon,
                        contentDescription = topLevelRoute.name
                    )
                },
                label = { Text(text = topLevelRoute.name) }
            )
        }
    }
}