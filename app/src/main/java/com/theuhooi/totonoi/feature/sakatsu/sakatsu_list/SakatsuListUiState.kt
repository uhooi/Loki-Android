package com.theuhooi.totonoi.feature.sakatsu.sakatsu_list

import androidx.compose.runtime.Stable

@Stable
data class SakatsuListUiState(
    val data: SakatsuListStatus = SakatsuListStatus.Loading,
    val errors: List<SakatsuListError> = listOf()
)

sealed interface SakatsuListStatus {
    object Empty : SakatsuListStatus
    data class Loaded(val sakatsuList: List<String>) : SakatsuListStatus
    object Loading : SakatsuListStatus
    object Error : SakatsuListStatus // FIXME: エラーの種類をハンドリングする必要あり？
}

sealed interface SakatsuListError {

}