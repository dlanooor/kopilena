package com.application.kopilena.ui.pemesanan

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.application.kopilena.data.pemesanan.Pemesanan
import com.application.kopilena.databinding.ActivityAddPemesananBinding
import com.application.kopilena.ui.pembayaran.AddPembayaranActivity

class AddPemesananActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityAddPemesananBinding
    private lateinit var viewModelAdd: AddPemesananViewModel
    private val spinnerList = ArrayList<String>()
    private lateinit var selectedBarang: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPemesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Add Pemesanan"
        val factory = PemesananViewModelFactory.getInstance(this)
        viewModelAdd = ViewModelProvider(this, factory).get(AddPemesananViewModel::class.java)

        viewModelAdd.barang.observe(this) { barangList ->
            for (barang in barangList) {
                spinnerList.add(barang.namaBarang)
            }
            binding.spinnerBarang.setOnItemSelectedListener(this)
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerBarang.adapter = adapter
        }

        binding.bayarButton.setOnClickListener {
            viewModelAdd.barang.observe(this) { barangList ->
                for (barang in barangList) {
                    if (selectedBarang.equals(barang.namaBarang)) {
                        val selectedAmount = Integer.parseInt(binding.jumlahEditText.text.toString())
                        val totalPrice = selectedAmount * barang.harga
                        viewModelAdd.insertPemesanan(
                            Pemesanan(nama = barang.namaBarang, jenis = barang.jenisBarang, jumlah = selectedAmount, total = totalPrice)
                        )

                        val intent = Intent(this, AddPembayaranActivity::class.java)
                        intent.putExtra("TOTAL", totalPrice)
                        startActivity(intent)
                        finish()
                    }

                }
            }
        }

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
        selectedBarang = spinnerList[position]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}