package com.application.kopilena.data.pemesanan

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pemesanan")
data class Pemesanan(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "kode")
    val kode: Int = 0,

    @ColumnInfo(name = "nama")
    val nama: String,

    @ColumnInfo(name = "jenis")
    val jenis: String,

    @ColumnInfo(name = "jumlah")
    val jumlah: Int,

    @ColumnInfo(name = "total")
    val total: Int
)