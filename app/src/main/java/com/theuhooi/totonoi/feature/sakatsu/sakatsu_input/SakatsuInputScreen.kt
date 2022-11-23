package com.theuhooi.totonoi.feature.sakatsu.sakatsu_input

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.theuhooi.totonoi.R
import com.theuhooi.totonoi.core.ui.components.LogCompositions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SakatsuInputScreen(
    uiState: SakatsuInputUiState,
    onNavigationClick: () -> Unit,
    onCompleteSave: () -> Unit,
    onShownError: () -> Unit,
    onSaveButtonClick: () -> Unit,
    onFacilityNameChange: (String) -> Unit,
    onVisitingDateChange: (Long) -> Unit,
    onSaunaTimeChange: (index: Int, saunaTime: String) -> Unit,
    onCoolBathTimeChange: (index: Int, coolBathTime: String) -> Unit,
    onRelaxationTimeChange: (index: Int, relaxationTime: String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onAddSaunaSetClick: () -> Unit
) {
    LogCompositions(tag = "SakatsuInputScreen")
    if (uiState.isCompleteSave) {
        Toast.makeText(
            LocalContext.current,
            stringResource(id = R.string.complete_save),
            Toast.LENGTH_SHORT
        ).show()
        onCompleteSave()
    }
    if (uiState.isError) {
        Toast.makeText(
            LocalContext.current,
            stringResource(id = R.string.unexpected_error),
            Toast.LENGTH_SHORT
        ).show()
        onShownError()
    }

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
                                onClick = onSaveButtonClick
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
                saunaSetList = uiState.saunaSetUiStateList,
                description = uiState.description,
                onFacilityNameChange = onFacilityNameChange,
                onVisitingDateChange = onVisitingDateChange,
                onSaunaTimeChange = onSaunaTimeChange,
                onCoolBathTimeChange = onCoolBathTimeChange,
                onRelaxationTimeChange = onRelaxationTimeChange,
                onDescriptionChange = onDescriptionChange,
                onAddSaunaSetClick = onAddSaunaSetClick
            )
        }
    }
    if (uiState.isLoading) {
        Surface(modifier = Modifier.fillMaxSize(), color = Color.Black.copy(alpha = 0.2f)) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}