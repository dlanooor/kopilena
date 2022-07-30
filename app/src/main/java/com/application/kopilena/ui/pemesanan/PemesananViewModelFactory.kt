package com.application.kopilena.ui.pemesanan

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.kopilena.data.barang.BarangRepository
import com.application.kopilena.data.pemesanan.PemesananRepository

class PemesananViewModelFactory private constructor(private val pemesananRepository: PemesananRepository, private val barangRepository: BarangRepository) :
    ViewModelProvider.Factory{

    companion object {
        @Volatile
        private var instance: PemesananViewModelFactory? = null

        fun getInstance(context: Context): PemesananViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: PemesananViewModelFactory(
                    PemesananRepository.getInstance(context),
                    BarangRepository.getInstance(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(PemesananViewModel::class.java) -> {
                PemesananViewModel(pemesananRepository) as T
            }
            modelClass.isAssignableFrom(AddPemesananViewModel::class.java) -> {
                AddPemesananViewModel(pemesananRepository, barangRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}