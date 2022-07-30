package com.application.kopilena.ui.barang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.application.kopilena.data.barang.Barang
import com.application.kopilena.databinding.ActivityBarangBinding

class BarangActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBarangBinding
    private lateinit var viewModel: BarangViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBarangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Data Barang"
        val factory = BarangViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(BarangViewModel::class.java)

        viewModel.barang.observe(this) {
            showRecyclerList(it)
        }

        binding.addButton.setOnClickListener {
            startActivity(Intent(this@BarangActivity, AddBarangActivity::class.java))
        }
    }

    private fun showRecyclerList(list: List<Barang>) {
        binding.rvBarang.setHasFixedSize(true)

        val arrayList = ArrayList<Barang>()
        arrayList.addAll(list)

        val adapter = BarangAdapter(arrayList)
        binding.rvBarang.adapter = adapter
    }
}