package com.application.kopilena.ui.pembayaran

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.kopilena.data.pembayaran.Pembayaran
import com.application.kopilena.databinding.ActivityPembayaranBinding

class PembayaranActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPembayaranBinding
    private lateinit var viewModel: PembayaranViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPembayaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Data Pembayaran"
        val factory = PembayaranViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(PembayaranViewModel::class.java)

        viewModel.pembayaran.observe(this) {
            showRecyclerList(it)
        }
    }

    private fun showRecyclerList(list: List<Pembayaran>) {
        binding.rvPembayaran.setHasFixedSize(true)
        binding.rvPembayaran.layoutManager = LinearLayoutManager(this)

        val arrayList = ArrayList<Pembayaran>()
        arrayList.addAll(list)

        val adapter = PembayaranAdapter(arrayList)
        binding.rvPembayaran.adapter = adapter
    }
}