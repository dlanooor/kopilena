package com.application.kopilena.ui.pelanggan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.application.kopilena.data.pelanggan.Pelanggan
import com.application.kopilena.databinding.ActivityAddPelangganBinding
import com.application.kopilena.ui.MainActivity

class AddPelangganActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddPelangganBinding
    private lateinit var viewModel: AddPelangganViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPelangganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Add Pelanggan"
        val factory = PelangganViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(AddPelangganViewModel::class.java)

        binding.addButton.setOnClickListener {
            val nama = binding.namaEditText.text.toString()
            val no_hp = binding.noHpEditText.text.toString()
            val no_antrian = binding.noAntrianEditText.text.toString().toInt()

            viewModel.insertPelanggan(Pelanggan(nama = nama, noHp = no_hp, noAntrian = no_antrian))
            startActivity(Intent(this@AddPelangganActivity, MainActivity::class.java))
            finish()
        }
    }
}