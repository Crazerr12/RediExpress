package com.example.deliveryapp.presentation.ui

import BottomItemScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.deliveryapp.R
import com.example.deliveryapp.presentation.navigation.Graph
import com.example.deliveryapp.presentation.navigation.RootNavGraph
import com.example.deliveryapp.presentation.navigation.isOnBackStack
import com.example.deliveryapp.presentation.theme.primaryColorLightTheme

@OptIn(ExperimentalMaterial3Api::class)
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
    val showTopBar = navController.isOnBackStack(Graph.BOTTOM)

    Scaffold(
        topBar = {
            if (showTopBar) {
                CenterAlignedTopAppBar(
                    title = { Text(text = "AppBar") },
                    navigationIcon = {
                        if (!showBottomBar) {
                            IconButton(
                                onClick = { navController.navigateUp() }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.arrow_back),
                                    contentDescription = stringResource(
                                        R.string.turn_back
                                    ),
                                    tint = primaryColorLightTheme,
                                )
                            }
                        }
                    },
                )
            }
        },
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
        label = {
            Text(
                text = stringResource(id = item.title),
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == item.route
        } == true,
        onClick = {
            navController.navigate(
                route = item.graph,
            ) {
                popUpTo(BottomItemScreen.HOME.route)
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