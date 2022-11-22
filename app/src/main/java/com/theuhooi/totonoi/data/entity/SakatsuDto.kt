package com.theuhooi.totonoi.data.entity

import java.time.LocalDateTime

data class SakatsuDto(
    val facilityName: String,
    val visitingDate: LocalDateTime,
    val saunaSets: List<SaunaSetDto>,
    val description: String?
)

data class SaunaSetDto(
    val saunaTime: Long?,
    val coolBathTime: Long?,
    val relaxationTime: Long?
)