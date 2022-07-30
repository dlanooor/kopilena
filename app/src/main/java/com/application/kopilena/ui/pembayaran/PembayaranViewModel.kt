package com.application.kopilena.ui.pembayaran

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.application.kopilena.data.pembayaran.Pembayaran
import com.application.kopilena.data.pembayaran.PembayaranRepository

class PembayaranViewModel(private val pembayaranRepository: PembayaranRepository) : ViewModel(){
    val pembayaran: LiveData<List<Pembayaran>> = pembayaranRepository.getPembayaran()
}