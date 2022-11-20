package com.theuhooi.totonoi.feature.sakatsu.sakatsu_list

import androidx.compose.runtime.Stable

@Stable
data class SakatsuListUiState(
    val data: SakatsuListStatus = SakatsuListStatus.Loading,
    val errors: List<SakatsuListError> = listOf()
)

sealed interface SakatsuListStatus {
    object Empty : SakatsuListStatus
    data class Loaded(val sakatsuList: List<SakatsuListItemUiState>) : SakatsuListStatus
    object Loading : SakatsuListStatus
    object Error : SakatsuListStatus // FIXME: エラーの種類をハンドリングする必要あり？
}

data class SakatsuListItemUiState(
    val title: String,
    val description: String? = null,
    val dateText: String,
    val saunaTimeText: String? = null,
    val coolBathTimeText: String? = null,
    val relaxationTimeText: String? = null
)

sealed interface SakatsuListError {

}