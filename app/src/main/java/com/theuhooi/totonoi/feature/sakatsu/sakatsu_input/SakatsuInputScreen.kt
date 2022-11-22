package com.theuhooi.totonoi.feature.sakatsu.sakatsu_input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.theuhooi.totonoi.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun SakatsuInputScreen(
    onNavigationClick: () -> Unit,
    viewModel: SakatsuInputViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.sakatsu_input_title))
                },
                navigationIcon = {
                    IconButton(onClick = onNavigationClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.talkback_back)
                        )
                    }
                },
                actions = {
                    Text(
                        modifier = Modifier
                            .clickable(
                                enabled = uiState.isSaveButtonEnabled,
                                onClick = viewModel::onSaveButtonClick
                            ),
                        text = stringResource(id = R.string.save),
                        color = if (uiState.isSaveButtonEnabled) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                }
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(top = contentPadding.calculateTopPadding())) {
            SakatsuInputSections(
                modifier = Modifier.fillMaxSize(),
                facilityName = uiState.facilityName,
                visitingDateText = uiState.visitingDateText,
                saunaSetList = uiState.saunaSetList,
                description = uiState.description,
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