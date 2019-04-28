package com.example.materialchipsandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_filter_chip_adapter.view.*

class FilterChipListAdapter : RecyclerView.Adapter<FilterViewHolder>() {

    var items = mutableListOf<Items>()


    fun setData(items: List<Items>){
        this.items = items as MutableList<Items>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.layout_filter_chip_adapter,parent, false)
        return FilterViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {

        val data = items[position]
        holder.textTitle.text = data.title
        holder.textCategory.text = data.category
    }
}

class FilterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val textTitle = view.text_title
    val textCategory = view.text_category

}
