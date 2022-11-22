package com.theuhooi.totonoi.data.usecase

import com.theuhooi.totonoi.data.entity.SakatsuDto
import com.theuhooi.totonoi.data.entity.SaunaSetDto
import com.theuhooi.totonoi.data.repository.SakatsuRepository
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

@Reusable
class ObserveSakatsuUseCase @Inject constructor(
    private val sakatsuRepository: SakatsuRepository
){
    operator fun invoke(): Flow<List<SakatsuDto>> {
        return sakatsuRepository.sakatsu.map {
            it.map {
                val instant = Instant.ofEpochMilli(it.sakatsu.visitingDate)
                val visitingDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                SakatsuDto(
                    facilityName = it.sakatsu.facilityName,
                    visitingDate = visitingDate,
                    saunaSets = it.saunaSets.map { saunaSet ->
                        SaunaSetDto(
                            saunaTime = saunaSet.saunaTime,
                            coolBathTime = saunaSet.coolBathTime,
                            relaxationTime = saunaSet.relaxationTime
                        )
                    },
                    description = it.sakatsu.description
                )
            }
        }
    }
}