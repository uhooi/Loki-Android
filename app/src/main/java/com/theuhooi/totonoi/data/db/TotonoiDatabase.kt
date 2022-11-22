package com.theuhooi.totonoi.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.theuhooi.totonoi.data.dao.SakatsuDao
import com.theuhooi.totonoi.data.dao.SaunaSetDao
import com.theuhooi.totonoi.data.entity.Sakatsu
import com.theuhooi.totonoi.data.entity.SaunaSet

@Database(entities = [Sakatsu::class, SaunaSet::class], version = 1, exportSchema = false)
abstract class TotonoiDatabase : RoomDatabase() {
    abstract fun sakatsuDao(): SakatsuDao
    abstract fun saunaSetDao(): SaunaSetDao
}