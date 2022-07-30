package com.application.kopilena.data.pelanggan

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pelanggan")
data class Pelanggan(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "nama")
    val nama: String,

    @ColumnInfo(name = "no_hp")
    val noHp: String,

    @ColumnInfo(name = "no_antrian")
    val noAntrian: Int
)