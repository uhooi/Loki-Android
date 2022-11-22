package com.theuhooi.totonoi.data.usecase

import com.theuhooi.totonoi.data.entity.SakatsuDto
import com.theuhooi.totonoi.data.entity.SaunaSetDto
import com.theuhooi.totonoi.data.repository.SakatsuRepository
import dagger.Reusable
import java.time.LocalDateTime
import javax.inject.Inject

@Reusable
class DeleteSakatsuUseCase @Inject constructor(
    private val sakatsuRepository: SakatsuRepository
){
    suspend operator fun invoke(
        facilityName: String,
        visitingDate: LocalDateTime,
        saunaSets: List<SaunaSetDto>,
        description: String?
    ) {
        sakatsuRepository.deleteSakatsu(
            SakatsuDto(
                facilityName = facilityName,
                visitingDate = visitingDate,
                saunaSets = saunaSets,
                description = description
            )
        )
    }
}