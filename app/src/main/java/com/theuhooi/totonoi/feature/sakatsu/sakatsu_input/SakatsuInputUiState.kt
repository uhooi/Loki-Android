package com.theuhooi.totonoi.feature.sakatsu.sakatsu_input

import androidx.compose.runtime.Stable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Stable
data class SakatsuInputUiState(
    val facilityName: String? = null,
    internal val visitingDate: LocalDateTime = LocalDateTime.now(),
    val saunaSetUiStateList: List<SaunaSetUiState> = listOf(SaunaSetUiState()),
    val description: String? = null,
    val isLoading: Boolean = false,
    val isCompleteSave: Boolean = false,
    val isError: Boolean = false
) {
    val visitingDateText: String =
        DateTimeFormatter.ofPattern("yyyy/MM/dd").format(visitingDate)

    val isSaveButtonEnabled: Boolean = facilityName.orEmpty().isNotBlank()
}

data class SaunaSetUiState(
    val saunaTime: String? = null,
    val coolBathTime: String? = null,
    val relaxationTime: String? = null
)