package com.application.kopilena.ui.barang

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.kopilena.R
import com.application.kopilena.data.barang.Barang


class BarangAdapter(private val listBarang : ArrayList<Barang>) : RecyclerView.Adapter<BarangAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_barang, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listBarang.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val byteArray = listBarang[position].image
        if (byteArray != null) {
            val decodedString = Base64.decode(listBarang[position].image, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            holder.ivBarang.setImageBitmap(bitmap)
        }

        holder.tvKodeBarang.text = listBarang[position].kodeBarang.toString()
        holder.tvJenisBarang.text = listBarang[position].jenisBarang
        holder.tvNamaBarang.text = listBarang[position].namaBarang
        holder.tvJumlah.text = "Jumlah = " + listBarang[position].jumlah.toString()
        holder.tvHarga.text = "Rp." + listBarang[position].harga.toString()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivBarang: ImageView = itemView.findViewById(R.id.iv_barang)
        val tvKodeBarang: TextView = itemView.findViewById(R.id.tv_kode_barang)
        val tvJenisBarang: TextView = itemView.findViewById(R.id.tv_jenis_barang)
        val tvNamaBarang: TextView = itemView.findViewById(R.id.tv_nama_barang)
        val tvJumlah: TextView = itemView.findViewById(R.id.tv_jumlah)
        val tvHarga: TextView = itemView.findViewById(R.id.tv_harga)
    }

}