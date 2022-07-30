package com.application.kopilena.ui.barang

import androidx.lifecycle.ViewModel
import com.application.kopilena.data.barang.Barang
import com.application.kopilena.data.barang.BarangRepository

class AddBarangViewModel(private val barangRepository: BarangRepository) : ViewModel() {
    fun insertBarang(barang: Barang) {
        barangRepository.insertBarang(barang)
    }
}