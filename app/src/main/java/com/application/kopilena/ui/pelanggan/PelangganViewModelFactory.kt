package com.application.kopilena.ui.pelanggan

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.kopilena.data.pelanggan.PelangganRepository

class PelangganViewModelFactory private constructor(private val pelangganRepository: PelangganRepository) :
    ViewModelProvider.Factory{

    companion object {
        @Volatile
        private var instance: PelangganViewModelFactory? = null

        fun getInstance(context: Context): PelangganViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: PelangganViewModelFactory(
                    PelangganRepository.getInstance(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(PelangganViewModel::class.java) -> {
                PelangganViewModel(pelangganRepository) as T
            }
            modelClass.isAssignableFrom(AddPelangganViewModel::class.java) -> {
                AddPelangganViewModel(pelangganRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}