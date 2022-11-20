package com.theuhooi.totonoi.core.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.theuhooi.totonoi.core.ui.navigator.TopDestination
import com.theuhooi.totonoi.core.ui.navigator.topNavGraph

@Composable
fun TotonoiApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = TopDestination.SakatsuList.route) {
        topNavGraph(navController = navController)
    }
}