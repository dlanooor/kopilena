package com.application.kopilena.ui.barang

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.kopilena.data.barang.BarangRepository

class BarangViewModelFactory private constructor(private val barangRepository: BarangRepository) :
    ViewModelProvider.Factory{

    companion object {
        @Volatile
        private var instance: BarangViewModelFactory? = null

        fun getInstance(context: Context): BarangViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: BarangViewModelFactory(
                    BarangRepository.getInstance(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(BarangViewModel::class.java) -> {
                BarangViewModel(barangRepository) as T
            }
            modelClass.isAssignableFrom(AddBarangViewModel::class.java) -> {
                AddBarangViewModel(barangRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}