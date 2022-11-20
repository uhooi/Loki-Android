package com.theuhooi.totonoi.core.ui.navigator

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.theuhooi.totonoi.feature.sakatsu.sakatsu_list.SakatsuListScreen

fun NavGraphBuilder.topNavGraph() {
    composable(TopDestination.SakatsuList.route) {
        SakatsuListScreen()
    }
    composable(TopDestination.SakatuInput.route) {
        TODO("implement SakatsuInputScreen")
    }
}

sealed class TopDestination(val route: String) {
    object SakatsuList : TopDestination("sakatsu-list")
    object SakatuInput : TopDestination("sakatsu-input")
}