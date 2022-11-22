package com.theuhooi.totonoi.feature.sakatsu.sakatsu_input

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class SakatsuInputViewModel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<SakatsuInputUiState> =
        MutableStateFlow(SakatsuInputUiState())
    val uiState: StateFlow<SakatsuInputUiState> = _uiState.asStateFlow()

    fun onFacilityNameChange(value: String) {
        _uiState.update {
            it.copy(facilityName = value)
        }
    }

    fun onVisitingDateChange(value: Long) {
        val instant = Instant.ofEpochMilli(value)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        _uiState.update {
            it.copy(visitingDate = localDateTime)
        }
    }

    fun onSaunaTimeChange(saunaIndex: Int, saunaTime: String) {
        _uiState.update { currentState ->
            currentState.copy(
                saunaSetUiStateList = currentState.saunaSetUiStateList.mapIndexed { index, item ->
                    if (index == saunaIndex) {
                        item.copy(saunaTime = saunaTime)
                    } else {
                        item
                    }
                }
            )
        }
    }

    fun onCoolBathTimeChange(saunaIndex: Int, coolBathTime: String) {
        _uiState.update { currentState ->
            currentState.copy(
                saunaSetUiStateList = currentState.saunaSetUiStateList.mapIndexed { index, item ->
                    if (index == saunaIndex) {
                        item.copy(coolBathTime = coolBathTime)
                    } else {
                        item
                    }
                }
            )
        }
    }

    fun onRelaxationTimeChange(saunaIndex: Int, relaxationTime: String) {
        _uiState.update { currentState ->
            currentState.copy(
                saunaSetUiStateList = currentState.saunaSetUiStateList.mapIndexed { index, item ->
                    if (index == saunaIndex) {
                        item.copy(relaxationTime = relaxationTime)
                    } else {
                        item
                    }
                }
            )
        }
    }

    fun onDescriptionChange(value: String) {
        _uiState.update {
            it.copy(description = value)
        }
    }

    fun onAddSaunaSetClick() {
        _uiState.update { currentState ->
            currentState.copy(
                saunaSetUiStateList = currentState.saunaSetUiStateList.plus(SaunaSetUiState())
            )
        }
    }

    fun onSaveButtonClick() {
        TODO("implement logic")
    }
}