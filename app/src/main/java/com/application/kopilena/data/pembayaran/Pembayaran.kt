package com.application.kopilena.data.pembayaran

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pembayaran")
data class Pembayaran(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "kode")
    val kode: Int = 0,

    @ColumnInfo(name = "total_bayar")
    val totalBayar: Int,

    @ColumnInfo(name = "total_uang")
    val totalUang: Int,

    @ColumnInfo(name = "total_kembali")
    val totalKembali: Int
)