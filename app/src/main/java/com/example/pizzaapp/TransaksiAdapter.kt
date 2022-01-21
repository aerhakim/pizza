package com.example.pizzaapp

import android.accessibilityservice.GestureDescription
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaapp.client.RetrofitClient
import com.example.pizzaapp.response.menu.MenuResponse
import com.squareup.picasso.Picasso


class TransaksiAdapter() : RecyclerView.Adapter<TransaksiAdapter.ViewHolder>(){



    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val textNama: TextView
        val textId: TextView
        val textHarga: TextView
        val imgMenu: ImageView

        init {
            textNama = v.findViewById(R.id.textNamaMakanan)
            textId = v.findViewById(R.id.textViewIdMakanan)
            textHarga = v.findViewById(R.id.textHargaMakanan)
            imgMenu = v.findViewById(R.id.imageMakanan)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.cardview_transaksi, parent, false)

        return ViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textId.text = listId[position].toString()
        holder.textHarga.text = listHarga[position].toString()
        holder.textNama.text = listNama[position]
        val gambar:String = listFoto[position]

        Picasso.get().load(RetrofitClient.IMAGES_URL+gambar).into(holder.imgMenu)

    }

    override fun getItemCount(): Int = listId.size

    companion object{
        var jumlah:Int = 0
        var listId = listOf<String>()
        var listNama = listOf<String>()
        var listHarga = listOf<Int>()
        var listFoto = listOf<String>()

        var harga:Int = 0
    }
}