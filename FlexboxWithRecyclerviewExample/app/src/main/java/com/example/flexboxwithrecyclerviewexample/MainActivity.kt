package com.example.flexboxwithrecyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayout
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    lateinit var recyclerviewitems: RecyclerView
    lateinit var adapterItems: AdapterItems
    lateinit var btnAddItem: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerviewitems = findViewById(R.id.recyclerviewItems)
        btnAddItem = findViewById(R.id.addItem)

        val flexboxLayoutManager = FlexboxLayoutManager(this)
        flexboxLayoutManager.apply {
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
        }
        val items = listOf("Android", "Jetpack compose","Material Design","Firebase","AWS","Retrofit")

        adapterItems = AdapterItems(items.toMutableList())

        recyclerviewitems.apply {
            layoutManager = flexboxLayoutManager
            adapter = adapterItems
        }

        btnAddItem.setOnClickListener {
            adapterItems.addNewItem(items.random())
        }






    }
}