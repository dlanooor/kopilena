package com.application.kopilena.ui.pelanggan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.kopilena.R
import com.application.kopilena.data.pelanggan.Pelanggan

class PelangganAdapter(private val listPelanggan : ArrayList<Pelanggan>) : RecyclerView.Adapter<PelangganAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_pelanggan, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listPelanggan.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.tvName.text = listPelanggan[position].nama
        holder.tvNoHp.text = listPelanggan[position].noHp
        holder.tvNoAntrian.text = "No.Antrian = " + listPelanggan[position].noAntrian
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tv_nama_pelanggan)
        val tvNoHp: TextView = itemView.findViewById(R.id.tv_no_hp_pelanggan)
        val tvNoAntrian: TextView = itemView.findViewById(R.id.tv_no_antrian)
//        val deleteButton: Button = itemView.findViewById(R.id.delete_button)
    }

}