package com.example.velmurugan.popupapp

import android.view.View

interface RecyclerviewCallbacks<T> {

    fun onItemClick(view: View, position: Int, item: T)

}