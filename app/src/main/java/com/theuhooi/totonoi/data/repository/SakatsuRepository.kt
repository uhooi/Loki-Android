package com.theuhooi.totonoi.data.repository

import com.theuhooi.totonoi.data.dao.SakatsuDao
import com.theuhooi.totonoi.data.dao.SaunaSetDao
import com.theuhooi.totonoi.data.entity.*
import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import java.time.ZoneId
import javax.inject.Inject

@Reusable
class SakatsuRepository @Inject constructor(
    private val sakatsuDao: SakatsuDao,
    private val saunaSetDao: SaunaSetDao
) {
    val sakatsu: Flow<List<SakatsuWithSaunaSets>> = sakatsuDao.observeSakatsuList()

    suspend fun registerSakatsu(sakatsuDto: SakatsuDto) {
        val visitingDateUnixTime =
            sakatsuDto.visitingDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val sakatsuId = sakatsuDao.insertSakatsu(
            Sakatsu(
                id = 0,
                facilityName = sakatsuDto.facilityName,
                visitingDate = visitingDateUnixTime,
                description = sakatsuDto.description
            )
        )
        sakatsuDto.saunaSets.forEach {
            saunaSetDao.insertSaunaSet(
                SaunaSet(
                    saunaSetId = 0,
                    sakatsuId = sakatsuId,
                    saunaTime = it.saunaTime,
                    coolBathTime = it.coolBathTime,
                    relaxationTime = it.relaxationTime
                )
            )
        }
    }

    suspend fun updateSakatsu(sakatsuDto: SakatsuDto) {
        val visitingDateUnixTime =
            sakatsuDto.visitingDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val sakatsuId = sakatsuDao.updateSakatsu(
            Sakatsu(
                id = 0,
                facilityName = sakatsuDto.facilityName,
                visitingDate = visitingDateUnixTime,
                description = sakatsuDto.description
            )
        )
        sakatsuDto.saunaSets.forEach {
            saunaSetDao.updateSaunaSet(
                SaunaSet(
                    saunaSetId = 0,
                    sakatsuId = sakatsuId,
                    saunaTime = it.saunaTime,
                    coolBathTime = it.coolBathTime,
                    relaxationTime = it.relaxationTime
                )
            )
        }
    }

    suspend fun deleteSakatsu(sakatsuDto: SakatsuDto) {
        val visitingDateUnixTime =
            sakatsuDto.visitingDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val sakatsuId = sakatsuDao.deleteSakatsu(
            Sakatsu(
                id = 0,
                facilityName = sakatsuDto.facilityName,
                visitingDate = visitingDateUnixTime,
                description = sakatsuDto.description
            )
        )
        sakatsuDto.saunaSets.forEach {
            saunaSetDao.deleteSaunaSet(
                SaunaSet(
                    saunaSetId = 0,
                    sakatsuId = sakatsuId,
                    saunaTime = it.saunaTime,
                    coolBathTime = it.coolBathTime,
                    relaxationTime = it.relaxationTime
                )
            )
        }
    }
}