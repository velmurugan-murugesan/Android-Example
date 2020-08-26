package com.example.bottomsheetandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_create_new.view.*

class RecyclerviewAdapter(val items: List<Item>) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_create_new, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = items[position]
        holder.name.text = item.name
        holder.img.background = ContextCompat.getDrawable(holder.itemView.context, item.image)
    }

}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name = view.name
    val img = view.img
}
