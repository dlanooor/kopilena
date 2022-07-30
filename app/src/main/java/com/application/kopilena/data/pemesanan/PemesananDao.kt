package com.application.kopilena.data.pemesanan

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PemesananDao {
    @Query("SELECT * FROM pemesanan")
    fun getPemesanan(): LiveData<List<Pemesanan>>

    @Insert
    fun insertPemesanan(pemesanan: Pemesanan)
}