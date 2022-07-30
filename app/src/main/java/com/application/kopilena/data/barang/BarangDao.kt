package com.application.kopilena.data.barang

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BarangDao {
    @Query("SELECT * FROM barang")
    fun getBarang() : LiveData<List<Barang>>

    @Insert
    fun insertBarang(barang: Barang)
}