package com.example.flexboxwithrecyclerviewexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdapterItems(private var items : MutableList<String>) : RecyclerView.Adapter<AdapterItems.AdapterItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item, parent, false)
        return AdapterItemViewHolder(view)
    }

    override fun getItemCount(): Int {
      return items.size
    }

    override fun onBindViewHolder(holder: AdapterItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addNewItem(itemName: String) {
        this.items.add(itemName)
        notifyItemInserted(items.size)
    }

    inner class AdapterItemViewHolder(view: View): ViewHolder(view) {

        private val tvItem = view.findViewById<Button>(R.id.btnItem)
        fun bind(item: String){
            tvItem.text = item
        }

    }
}