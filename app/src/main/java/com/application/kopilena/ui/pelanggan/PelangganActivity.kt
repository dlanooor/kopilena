package com.application.kopilena.ui.pelanggan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.kopilena.data.pelanggan.Pelanggan
import com.application.kopilena.databinding.ActivityPelangganBinding

class PelangganActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPelangganBinding
    private lateinit var viewModel: PelangganViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPelangganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Data Pelanggan"
        val factory = PelangganViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(PelangganViewModel::class.java)

        viewModel.pelanggan.observe(this) {
            showRecyclerList(it)
        }

        binding.addButton.setOnClickListener {
            startActivity(Intent(this@PelangganActivity, AddPelangganActivity::class.java))
        }
    }

    private fun showRecyclerList(list: List<Pelanggan>) {
        binding.rvPelanggan.setHasFixedSize(true)
        binding.rvPelanggan.layoutManager = LinearLayoutManager(this)

        val arrayList = ArrayList<Pelanggan>()
        arrayList.addAll(list)

        val adapter = PelangganAdapter(arrayList)
        binding.rvPelanggan.adapter = adapter
    }

}