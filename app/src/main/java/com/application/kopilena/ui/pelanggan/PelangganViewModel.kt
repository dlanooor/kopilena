package com.application.kopilena.ui.pelanggan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.application.kopilena.data.pelanggan.Pelanggan
import com.application.kopilena.data.pelanggan.PelangganRepository

class PelangganViewModel(private val pelangganRepository: PelangganRepository) : ViewModel(){
    val pelanggan: LiveData<List<Pelanggan>> = pelangganRepository.getPelanggan()

    suspend fun deletePelanggan(pelanggan: Pelanggan) {
        pelangganRepository.deletePelanggan(pelanggan)
    }
}