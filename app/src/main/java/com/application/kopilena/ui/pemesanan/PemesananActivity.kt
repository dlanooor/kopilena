package com.application.kopilena.ui.pemesanan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.kopilena.data.pemesanan.Pemesanan
import com.application.kopilena.databinding.ActivityPemesananBinding

class PemesananActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPemesananBinding
    private lateinit var viewModel: PemesananViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Data Pemesanan"
        val factory = PemesananViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(PemesananViewModel::class.java)

        viewModel.pemesanan.observe(this) {
            showRecyclerList(it)
        }

        binding.addButton.setOnClickListener {
            startActivity(Intent(this@PemesananActivity, AddPemesananActivity::class.java))
        }
    }

    private fun showRecyclerList(list: List<Pemesanan>) {
        binding.rvPemesanan.setHasFixedSize(true)
        binding.rvPemesanan.layoutManager = LinearLayoutManager(this)

        val arrayList = ArrayList<Pemesanan>()
        arrayList.addAll(list)

        val adapter = PemesananAdapter(arrayList)
        binding.rvPemesanan.adapter = adapter
    }
}