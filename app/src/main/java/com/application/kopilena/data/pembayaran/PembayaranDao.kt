package com.application.kopilena.data.pembayaran

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PembayaranDao {
    @Query("SELECT * FROM pembayaran")
    fun getPembayaran(): LiveData<List<Pembayaran>>

    @Insert
    fun insertPembayaran(pembayaran: Pembayaran)
}