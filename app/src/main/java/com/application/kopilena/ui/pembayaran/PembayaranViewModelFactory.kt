package com.application.kopilena.ui.pembayaran

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.kopilena.data.pembayaran.PembayaranRepository

class PembayaranViewModelFactory private constructor(private val pembayaranRepository: PembayaranRepository) :
    ViewModelProvider.Factory{

    companion object {
        @Volatile
        private var instance: PembayaranViewModelFactory? = null

        fun getInstance(context: Context): PembayaranViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: PembayaranViewModelFactory(
                    PembayaranRepository.getInstance(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(PembayaranViewModel::class.java) -> {
                PembayaranViewModel(pembayaranRepository) as T
            }
            modelClass.isAssignableFrom(AddPembayaranViewModel::class.java) -> {
                AddPembayaranViewModel(pembayaranRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}