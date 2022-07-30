package com.application.kopilena.data.pelanggan

import android.content.Context
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class PelangganRepository(private val pelangganDao: PelangganDao) {
    companion object {
        @Volatile
        private var instance: PelangganRepository? = null

        fun getInstance(context: Context): PelangganRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = PelangganDatabase.getInstance(context)
                    instance = PelangganRepository(database.pelangganDao())
                }
                return instance as PelangganRepository
            }
        }
    }

    fun getPelanggan(): LiveData<List<Pelanggan>> {
        return pelangganDao.getPelanggan()
    }

    fun insertPelanggan(pelanggan: Pelanggan) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            pelangganDao.insertPelanggan(pelanggan)
        }
    }

    fun deletePelanggan(pelanggan: Pelanggan) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            pelangganDao.deletePelanggan(pelanggan)
        }
    }
}