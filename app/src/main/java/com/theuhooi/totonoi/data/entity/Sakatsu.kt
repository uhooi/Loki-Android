package com.theuhooi.totonoi.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class Sakatsu(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val facilityName: String,
    val visitingDate: Long, // Unix Time
    val description: String?,
)

@Entity
data class SaunaSet(
    @PrimaryKey(autoGenerate = true)
    val saunaSetId: Long,
    val sakatsuId: Long,
    val saunaTime: Long?, // ms
    val coolBathTime: Long?, // ms
    val relaxationTime: Long? // ms
)

data class SakatsuWithSaunaSets(
    @Embedded val sakatsu: Sakatsu,
    @Relation(
        parentColumn = "id",
        entityColumn = "sakatsuId"
    )
    val saunaSets: List<SaunaSet>
)