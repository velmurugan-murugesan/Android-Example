package com.example.navigationrailexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationrailexample.databinding.AdapterMailBinding

class MailAdapter : RecyclerView.Adapter<MenuViewHolder>() {

    private var itemList = mutableListOf<Mail>()

    fun addMailList(list: List<Mail>) {
        this.itemList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMailBinding.inflate(inflater, parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = itemList[position]
        holder.adapterMailBinding.apply {
            this.textFrom.text = item.from
            this.textSubject.text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since"
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}

class MenuViewHolder(val adapterMailBinding: AdapterMailBinding): RecyclerView.ViewHolder(adapterMailBinding.root)