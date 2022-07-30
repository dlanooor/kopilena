package com.application.kopilena.data.pembayaran

import android.content.Context
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class PembayaranRepository(private val pembayaranDao: PembayaranDao) {
    companion object {
        @Volatile
        private var instance: PembayaranRepository? = null

        fun getInstance(context: Context): PembayaranRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = PembayaranDatabase.getInstance(context)
                    instance = PembayaranRepository(database.pembayaranDao())
                }
                return instance as PembayaranRepository
            }
        }
    }

    fun getPembayaran(): LiveData<List<Pembayaran>> {
        return pembayaranDao.getPembayaran()
    }

    fun insertPembayaran(pembayaran: Pembayaran) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            pembayaranDao.insertPembayaran(pembayaran)
        }
    }
}