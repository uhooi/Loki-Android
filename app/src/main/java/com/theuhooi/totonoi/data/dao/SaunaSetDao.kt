package com.theuhooi.totonoi.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.theuhooi.totonoi.data.entity.SaunaSet

@Dao
interface SaunaSetDao {
    @Insert
    suspend fun insertSaunaSet(saunaSet: SaunaSet)

    @Update
    suspend fun updateSaunaSet(saunaSet: SaunaSet)

    @Delete
    suspend fun deleteSaunaSet(saunaSet: SaunaSet)
}