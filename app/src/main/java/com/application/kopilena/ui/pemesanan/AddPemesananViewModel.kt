package com.application.kopilena.ui.pemesanan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.application.kopilena.data.barang.Barang
import com.application.kopilena.data.barang.BarangRepository
import com.application.kopilena.data.pemesanan.Pemesanan
import com.application.kopilena.data.pemesanan.PemesananRepository

class AddPemesananViewModel (private val pemesananRepository: PemesananRepository, private val barangRepository: BarangRepository) : ViewModel() {
    val barang: LiveData<List<Barang>> = barangRepository.getBarang()

    fun insertPemesanan(pemesanan: Pemesanan) {
        pemesananRepository.insertPemesanan(pemesanan)
    }
}