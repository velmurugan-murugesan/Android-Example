package com.velmurugan.recyclerviewdiffutil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val userList: MutableList<Users>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    fun setUserList(updatedUserList: List<Users>) {
        val diffResult = DiffUtil.calculateDiff(UserDiffUtilCallback(userList, updatedUserList))
        userList.clear()
        userList.addAll(updatedUserList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.username.text = user.name
        holder.address.text = user.address
    }

    override fun getItemCount(): Int {
        return userList.size
    }
    inner class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val username: AppCompatTextView = view.findViewById(R.id.username)
        val address: AppCompatTextView = view.findViewById(R.id.address)

    }
}
