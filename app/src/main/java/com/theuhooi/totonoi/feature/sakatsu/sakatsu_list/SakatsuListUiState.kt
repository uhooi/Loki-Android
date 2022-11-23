package com.theuhooi.totonoi.feature.sakatsu.sakatsu_list

import androidx.compose.runtime.Stable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
    val title: String, // TODO: facilityNameに統一
    val description: String? = null,
    private val visitingDate: LocalDateTime,
    val saunaTimeText: String? = null,
    val coolBathTimeText: String? = null,
    val relaxationTimeText: String? = null
) {
    val visitingDateText: String
        get() = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(visitingDate)
}

sealed interface SakatsuListError {

}