package com.theuhooi.totonoi.feature.sakatsu.sakatsu_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SakatsuListViewModel @Inject constructor() : ViewModel() {

    private val _uiState: MutableStateFlow<SakatsuListUiState> =
        MutableStateFlow(SakatsuListUiState())
    val uiState: StateFlow<SakatsuListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            delay(1000) // FIXME: 擬似的なIO処理
            _uiState.update { currentState ->
                currentState.copy(
                    data = SakatsuListStatus.Loaded(
                        sakatsuList = (1..100).map {
                            SakatsuListItemUiState(
                                title = "title$it",
                                description = if (it % 2 == 0) "description$it" else null,
                                dateText = "2022/11/$it",
                                saunaTimeText = if (it % 3 == 0) "$it" else null,
                                coolBathTimeText = if (it % 4 == 0) "$it" else null,
                                relaxationTimeText = if (it % 5 == 0) "$it" else null
                            )
                        }
                    )
                )
            }
        }
    }
}