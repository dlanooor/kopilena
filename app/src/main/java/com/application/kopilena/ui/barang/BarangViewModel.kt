package com.application.kopilena.ui.barang

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.application.kopilena.data.barang.Barang
import com.application.kopilena.data.barang.BarangRepository

class BarangViewModel(private val barangRepository: BarangRepository) : ViewModel(){
    val barang: LiveData<List<Barang>> = barangRepository.getBarang()
}