package com.application.kopilena.ui.pembayaran

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.application.kopilena.data.pembayaran.Pembayaran
import com.application.kopilena.databinding.ActivityAddPembayaranBinding
import com.application.kopilena.ui.MainActivity

class AddPembayaranActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPembayaranBinding
    private lateinit var viewModel: AddPembayaranViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPembayaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Add Pembayaran"
        val factory = PembayaranViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(AddPembayaranViewModel::class.java)

        val totalBayar = intent.getIntExtra("TOTAL", 0)
        binding.tvTotalBayar.text = "Rp." + totalBayar.toString()

        binding.bayarButton.setOnClickListener {
            val totalUangString = binding.uangEditText.text.toString()
            val totalUangInt = Integer.parseInt(totalUangString)
            viewModel.insertPembayaran(Pembayaran(totalBayar = totalBayar, totalUang = totalUangInt, totalKembali = totalUangInt - totalBayar))

            startActivity(Intent(this@AddPembayaranActivity, MainActivity::class.java))
            finish()
        }

    }
}