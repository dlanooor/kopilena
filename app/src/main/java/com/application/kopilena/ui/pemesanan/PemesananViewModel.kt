package com.application.kopilena.ui.pemesanan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.application.kopilena.data.pemesanan.Pemesanan
import com.application.kopilena.data.pemesanan.PemesananRepository

class PemesananViewModel(private val pemesananRepository: PemesananRepository) : ViewModel(){
    val pemesanan: LiveData<List<Pemesanan>> = pemesananRepository.getPemesanan()
}