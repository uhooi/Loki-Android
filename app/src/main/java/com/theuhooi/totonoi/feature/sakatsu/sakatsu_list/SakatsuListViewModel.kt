package com.theuhooi.totonoi.feature.sakatsu.sakatsu_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theuhooi.totonoi.data.usecase.ObserveSakatsuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SakatsuListViewModel @Inject constructor(
    observeSakatsuUseCase: ObserveSakatsuUseCase
) : ViewModel() {

    val uiState: StateFlow<SakatsuListUiState> = observeSakatsuUseCase().map {
        if (it.isEmpty()) {
            SakatsuListUiState(
                data = SakatsuListStatus.Empty
            )
        } else {
            SakatsuListUiState(
                data = SakatsuListStatus.Loaded(
                    sakatsuList = it.map {
                        SakatsuListItemUiState(
                            title = it.facilityName,
                            description = it.description,
                            visitingDate = it.visitingDate,
                            saunaTimeText = if (it.saunaSets.isNotEmpty()) {
                                it.saunaSets.first().saunaTime?.let {
                                    "${it / 60000}"
                                }
                            } else {
                                null
                            },
                            coolBathTimeText = if (it.saunaSets.isNotEmpty()) {
                                it.saunaSets.first().coolBathTime?.let {
                                    "${it / 1000}"
                                }
                            } else {
                                null
                            },
                            relaxationTimeText = if (it.saunaSets.isNotEmpty()) {
                                it.saunaSets.first().relaxationTime?.let {
                                    "${it / 60000}"
                                }
                            } else {
                                null
                            }
                        )
                    }
                )
            )
        }
    }.catch {
        emit(SakatsuListUiState(data = SakatsuListStatus.Empty))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), initialValue = SakatsuListUiState())

}