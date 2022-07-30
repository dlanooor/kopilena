package com.application.kopilena.ui.pembayaran

import androidx.lifecycle.ViewModel
import com.application.kopilena.data.pembayaran.Pembayaran
import com.application.kopilena.data.pembayaran.PembayaranRepository

class AddPembayaranViewModel (private val pembayaranRepository: PembayaranRepository) : ViewModel() {
    fun insertPembayaran(pembayaran: Pembayaran) {
        pembayaranRepository.insertPembayaran(pembayaran)
    }
}