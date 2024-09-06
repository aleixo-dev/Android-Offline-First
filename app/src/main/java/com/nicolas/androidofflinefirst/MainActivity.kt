package com.nicolas.androidofflinefirst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nicolas.androidofflinefirst.ui.theme.AndroidOfflineFirstTheme
import com.nicolas.androidofflinefirst.ui.theme.home.HomeRoute

data class Item(
    val icon: ImageVector,
    val title: String,
    val route: String
)

class MainActivity : ComponentActivity() {

    val navItems = listOf(
        Item(
            icon = Icons.Rounded.Home,
            title = "Home",
            route = "home"
        ),
        Item(
            icon = Icons.Rounded.Favorite,
            title = "Favorites",
            route = "favorites"
        ),
        Item(
            icon = Icons.Rounded.Notifications,
            title = "Notifications",
            route = "notifications"
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidOfflineFirstTheme {

                val navController = rememberNavController()
                var clicked by remember { mutableStateOf(false) }

                Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                    NavigationBar(containerColor = MaterialTheme.colorScheme.surfaceContainer) {

                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        navItems.forEach { item ->
                            NavigationBarItem(
                                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                                onClick = { navController.navigate(item.route) },
                                icon = {
                                    Icon(
                                        item.icon,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                },
                                label = {
                                    Text(
                                        item.title,
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                }
                            )
                        }
                    }
                }) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") {
                            HomeRoute()
                        }
                        composable("favorites") {
                            HomeRoute()
                        }
                        composable("notifications") {
                            HomeRoute()
                        }
                    }
                }
            }
        }
    }
}