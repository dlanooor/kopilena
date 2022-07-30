package com.application.kopilena.data.pemesanan

import android.content.Context
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class PemesananRepository (private val pemesananDao: PemesananDao) {
    companion object {
        @Volatile
        private var instance: PemesananRepository? = null

        fun getInstance(context: Context): PemesananRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = PemesananDatabase.getInstance(context)
                    instance = PemesananRepository(database.pemesananDao())
                }
                return instance as PemesananRepository
            }
        }
    }

    fun getPemesanan(): LiveData<List<Pemesanan>> {
        return pemesananDao.getPemesanan()
    }

    fun insertPemesanan(pemesanan: Pemesanan) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            pemesananDao.insertPemesanan(pemesanan)
        }
    }
}