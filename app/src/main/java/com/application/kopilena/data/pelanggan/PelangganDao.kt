package com.application.kopilena.data.pelanggan

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PelangganDao {
    @Query("SELECT * FROM pelanggan")
    fun getPelanggan() : LiveData<List<Pelanggan>>

    @Insert
    fun insertPelanggan(pelanggan: Pelanggan)

    @Delete
    fun deletePelanggan(pelanggan: Pelanggan)
}