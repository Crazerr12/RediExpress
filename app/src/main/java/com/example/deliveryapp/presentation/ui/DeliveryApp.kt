package com.example.deliveryapp.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.deliveryapp.presentation.navigation.BottomItemScreen
import com.example.deliveryapp.presentation.navigation.RootNavGraph

@Composable
fun DeliveryApp(
    navController: NavHostController = rememberNavController(),
    startDestination: String,
) {
    val bottomItems = BottomItemScreen.entries.toTypedArray()
    val bottomBarRoutes = bottomItems.map { it.route }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = currentDestination?.route in bottomBarRoutes

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomAppBar {
                    bottomItems.forEach { item ->
                        AddItem(
                            item = item,
                            currentDestination = currentDestination,
                            navController = navController
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            RootNavGraph(
                navController = navController,
                startDestination = startDestination,
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    item: BottomItemScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    NavigationBarItem(
        label = { Text(text = stringResource(id = item.title)) },
        selected = currentDestination?.hierarchy?.any() {
            it.route == item.route
        } == true,
        onClick = {
            navController.navigate(
                route = item.route,
            ) {
                navController.currentBackStackEntry?.destination?.parent?.findStartDestination()?.route?.let {
                    popUpTo(
                        it
                    )
                }
                launchSingleTop = true
            }
        },
        icon = {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = "Navigaton icon"
            )
        }
    )
}