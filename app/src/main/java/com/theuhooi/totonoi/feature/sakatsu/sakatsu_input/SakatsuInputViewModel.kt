package com.theuhooi.totonoi.feature.sakatsu.sakatsu_input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theuhooi.totonoi.data.entity.SaunaSetDto
import com.theuhooi.totonoi.data.usecase.RegisterSakatsuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class SakatsuInputViewModel @Inject constructor(
    private val registerSakatsuUseCase: RegisterSakatsuUseCase
) : ViewModel() {

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
        // facilityNameが空の場合はsave buttonが押せないがfail safeとして
        val facilityName = uiState.value.facilityName ?: return
        _uiState.update {
            it.copy(isLoading = true)
        }
        val saunaSets = uiState.value.saunaSetUiStateList.map {
            SaunaSetDto(
                saunaTime = it.saunaTime?.toLong()?.let {
                    it * 60000
                },
                coolBathTime = it.coolBathTime?.toLong()?.let {
                    it * 1000
                },
                relaxationTime = it.relaxationTime?.toLong()?.let {
                    it * 60000
                }
            )
        }
        viewModelScope.launch {
            runCatching {
                registerSakatsuUseCase(
                    facilityName = facilityName,
                    visitingDate = uiState.value.visitingDate,
                    saunaSets = saunaSets,
                    description = uiState.value.description
                )
            }.onSuccess {
                _uiState.update {
                    it.copy(isLoading = false, isCompleteSave = true)
                }
            }.onFailure {
                Timber.w(it)
                _uiState.update {
                    it.copy(isLoading = false, isError = true)
                }
            }
        }
    }

    fun onCompleteShown() {
        _uiState.update {
            it.copy(isCompleteSave = false)
        }
    }

    fun onShownError() {
        _uiState.update {
            it.copy(isError = false)
        }
    }

}