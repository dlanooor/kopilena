package com.application.kopilena.ui.pembayaran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.kopilena.R
import com.application.kopilena.data.pembayaran.Pembayaran

class PembayaranAdapter (private val listPembayaran : ArrayList<Pembayaran>) : RecyclerView.Adapter<PembayaranAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_pembayaran, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listPembayaran.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.tvPembayaran.text = "Kode Pembayaran : " + listPembayaran[position].kode.toString()
        holder.tvTotalBayar.text = "Total Bayar : Rp." + listPembayaran[position].totalBayar.toString()
        holder.tvTotalUang.text = "Total Uang : Rp." + listPembayaran[position].totalUang.toString()
        holder.tvTotalKembali.text = "Total Kembali : Rp." + listPembayaran[position].totalKembali.toString()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvPembayaran: TextView = itemView.findViewById(R.id.tv_kode_pembayaran)
        val tvTotalBayar: TextView = itemView.findViewById(R.id.tv_total_bayar)
        val tvTotalUang: TextView = itemView.findViewById(R.id.tv_total_uang)
        val tvTotalKembali: TextView = itemView.findViewById(R.id.tv_total_kembali)
    }

}