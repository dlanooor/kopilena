package com.application.kopilena.ui.pemesanan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.kopilena.R
import com.application.kopilena.data.pemesanan.Pemesanan

class PemesananAdapter (private val listPemesanan : ArrayList<Pemesanan>) : RecyclerView.Adapter<PemesananAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_pemesanan, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listPemesanan.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.tvKode.text = "Kode Pemesanan : " + listPemesanan[position].kode
        holder.tvNama.text = listPemesanan[position].nama
        holder.tvJenis.text = listPemesanan[position].jenis
        holder.tvJumlah.text = "Jumlah : " + listPemesanan[position].jumlah.toString()
        holder.tvTotal.text = "Total : Rp." + listPemesanan[position].total.toString()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvKode: TextView = itemView.findViewById(R.id.tv_kode_pemesanan)
        val tvNama: TextView = itemView.findViewById(R.id.tv_nama_pemesanan)
        val tvJenis: TextView = itemView.findViewById(R.id.tv_jenis_pemesanan)
        val tvJumlah: TextView = itemView.findViewById(R.id.tv_jumlah_pemesanan)
        val tvTotal: TextView = itemView.findViewById(R.id.tv_total_pemesanan)
    }

}