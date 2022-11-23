package com.theuhooi.totonoi.core.ui

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.theuhooi.totonoi.core.ui.navigator.TopDestination
import com.theuhooi.totonoi.feature.sakatsu.sakatsu_input.SakatsuInputScreen
import com.theuhooi.totonoi.feature.sakatsu.sakatsu_input.SakatsuInputViewModel
import com.theuhooi.totonoi.feature.sakatsu.sakatsu_list.SakatsuListScreen

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun TotonoiApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = TopDestination.SakatsuList.route) {
        composable(TopDestination.SakatsuList.route) {
            SakatsuListScreen(
                onFabClick = {
                    navController.navigate(TopDestination.SakatuInput.route)
                }
            )
        }
        composable(TopDestination.SakatuInput.route) {
            val viewModel: SakatsuInputViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            SakatsuInputScreen(
                uiState = uiState,
                onNavigationClick = remember(viewModel) {
                    { navController.popBackStack() }
                },
                onCompleteSave = remember(viewModel) {
                    {
                        navController.navigateUp()
                        viewModel.onCompleteShown()
                    }
                },
                onShownError = viewModel::onShownError,
                onSaveButtonClick = viewModel::onSaveButtonClick,
                onFacilityNameChange = viewModel::onFacilityNameChange,
                onVisitingDateChange = viewModel::onVisitingDateChange,
                onSaunaTimeChange = viewModel::onSaunaTimeChange,
                onCoolBathTimeChange = viewModel::onCoolBathTimeChange,
                onRelaxationTimeChange = viewModel::onRelaxationTimeChange,
                onDescriptionChange = viewModel::onDescriptionChange,
                onAddSaunaSetClick = viewModel::onAddSaunaSetClick
            )
        }
    }
}