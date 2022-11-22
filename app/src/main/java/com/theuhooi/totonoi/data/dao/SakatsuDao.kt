package com.theuhooi.totonoi.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.theuhooi.totonoi.data.entity.Sakatsu
import com.theuhooi.totonoi.data.entity.SakatsuWithSaunaSets
import kotlinx.coroutines.flow.Flow

@Dao
interface SakatsuDao {

    @Transaction
    @Query("SELECT * FROM Sakatsu")
    fun observeSakatsuList(): Flow<List<SakatsuWithSaunaSets>>

    @Insert
    suspend fun insertSakatsu(sakatsu: Sakatsu): Long

    @Update
    suspend fun updateSakatsu(sakatsu: Sakatsu): Long

    @Delete
    suspend fun deleteSakatsu(sakatsu: Sakatsu): Long
}