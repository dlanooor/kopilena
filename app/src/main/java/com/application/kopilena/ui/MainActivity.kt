package com.application.kopilena.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.kopilena.databinding.ActivityMainBinding
import com.application.kopilena.ui.barang.BarangActivity
import com.application.kopilena.ui.pelanggan.PelangganActivity
import com.application.kopilena.ui.pembayaran.PembayaranActivity
import com.application.kopilena.ui.pemesanan.PemesananActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardViewBarang.setOnClickListener {
            startActivity(Intent(this@MainActivity, BarangActivity::class.java))
        }

        binding.cardViewPelanggan.setOnClickListener {
            startActivity(Intent(this@MainActivity, PelangganActivity::class.java))
        }

        binding.cardViewPemesanan.setOnClickListener {
            startActivity(Intent(this@MainActivity, PemesananActivity::class.java))
        }

        binding.cardViewPembayaran.setOnClickListener {
            startActivity(Intent(this@MainActivity, PembayaranActivity::class.java))
        }
    }
}