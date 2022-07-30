package com.application.kopilena.ui.pelanggan

import androidx.lifecycle.ViewModel
import com.application.kopilena.data.pelanggan.Pelanggan
import com.application.kopilena.data.pelanggan.PelangganRepository

class AddPelangganViewModel(private val pelangganRepository: PelangganRepository) : ViewModel() {
    fun insertPelanggan(pelanggan: Pelanggan) {
        pelangganRepository.insertPelanggan(pelanggan)
    }
}