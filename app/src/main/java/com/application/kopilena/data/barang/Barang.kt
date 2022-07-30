package com.application.kopilena.data.barang

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang")
data class Barang(
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "kode_barang")
    val kodeBarang: Int = 0,

    @ColumnInfo(name = "nama_barang")
    val namaBarang: String,

    @ColumnInfo(name = "jenis_barang")
    val jenisBarang: String,

    @ColumnInfo(name = "jumlah")
    val jumlah: Int,

    @ColumnInfo(name = "harga")
    val harga: Int,

    @ColumnInfo(name = "image")
    var image: String? = null
)