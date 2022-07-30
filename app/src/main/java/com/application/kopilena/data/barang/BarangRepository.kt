package com.application.kopilena.data.barang

import android.content.Context
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class BarangRepository(private val barangDao: BarangDao) {
    companion object {
        @Volatile
        private var instance: BarangRepository? = null

        fun getInstance(context: Context): BarangRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = BarangDatabase.getInstance(context)
                    instance = BarangRepository(database.barangDao())
                }
                return instance as BarangRepository
            }
        }
    }

    fun getBarang(): LiveData<List<Barang>> {
        return barangDao.getBarang()
    }

    fun insertBarang(barang: Barang) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            barangDao.insertBarang(barang)
        }
    }
}