package com.example.koinandroidexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_user_adapter.view.*

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    var userList = mutableListOf<Users>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_user_adapter, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.title.text = user.title
    }

    fun setItems(it: List<Users>) {
        this.userList = it.toMutableList()
        notifyDataSetChanged()
    }

}

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val title = view.user_item_title

}
