package com.theuhooi.totonoi.di

import android.content.Context
import androidx.room.Room
import com.theuhooi.totonoi.data.dao.SakatsuDao
import com.theuhooi.totonoi.data.dao.SaunaSetDao
import com.theuhooi.totonoi.data.db.TotonoiDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    companion object {
        @Provides
        @Singleton
        fun providesTotonoiDatabase(
            @ApplicationContext context: Context
        ): TotonoiDatabase {
            return Room.databaseBuilder(
                context,
                TotonoiDatabase::class.java,
                "totonoi_db"
            ).build()
        }

        @Provides
        fun providesSakatsuDao(
            totonoiDatabase: TotonoiDatabase
        ): SakatsuDao {
            return totonoiDatabase.sakatsuDao()
        }

        @Provides
        fun providesSaunaSetDao(
            totonoiDatabase: TotonoiDatabase
        ): SaunaSetDao {
            return totonoiDatabase.saunaSetDao()
        }
    }
}