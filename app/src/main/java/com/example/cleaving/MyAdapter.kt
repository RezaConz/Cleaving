package com.example.cleaving

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleaving.LoginRegistrasi.Barang
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(private val List:ArrayList<Barang>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    class MyViewHolder(itemview:View): RecyclerView.ViewHolder(itemview){

        val image:ShapeableImageView = itemview.findViewById(R.id.gambar)
        val nama:TextView = itemview.findViewById(R.id.nama)
        val poin:TextView = itemview.findViewById(R.id.poin)
        val jumlah:TextView = itemview.findViewById(R.id.jumlah)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemview)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = List[position]
        holder.image.setImageResource(currentItem.image)
        holder.nama.text = currentItem.nama
        holder.poin.text = currentItem.poin
        holder.jumlah.text = currentItem.jumlah
    }
}