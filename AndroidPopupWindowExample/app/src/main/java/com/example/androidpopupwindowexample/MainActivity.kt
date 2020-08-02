package com.example.androidpopupwindowexample

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var filterPopup:PopupWindow? = null
    private var selectedItem: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        optionMore.setOnClickListener {
            dismissPopup()
            filterPopup = showAlertFilter()
            filterPopup?.isOutsideTouchable = true
            filterPopup?.isFocusable = true
            filterPopup?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            filterPopup?.showAsDropDown(optionMore)
        }

        filter.setOnClickListener {
            dismissPopup()
            filterPopup = showAlertFilter()
            filterPopup?.isOutsideTouchable = true
            filterPopup?.isFocusable = true
            filterPopup?.showAsDropDown(filter)
        }
    }

    override fun onStop() {
        super.onStop()
        dismissPopup()
    }

    private fun dismissPopup() {
        filterPopup?.let {
            if(it.isShowing){
                it.dismiss()
            }
            filterPopup = null
        }

    }

    private fun getFilterItems() : List<FilterItem> {

        val filterItemList = mutableListOf<FilterItem>()
        filterItemList.add(FilterItem(R.drawable.ic_desktop_mac_black_24dp,"Hotel"))
        filterItemList.add(FilterItem(R.drawable.ic_desktop_mac_black_24dp,"Rooms"))
        filterItemList.add(FilterItem(R.drawable.ic_desktop_mac_black_24dp,"Test Data One"))
        filterItemList.add(FilterItem(R.drawable.ic_desktop_mac_black_24dp,"Hotel Rooms"))

        return filterItemList
    }

    private fun showAlertFilter(): PopupWindow {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.alter_filter_layout, null)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        val adapter = AlertFilterAdapter(this)
        adapter.addAlertFilter(getFilterItems())
        recyclerView.adapter = adapter
        adapter.selectedItem(selectedItem)

        adapter.setOnClick(object : RecyclerviewCallbacks<FilterItem> {
            override fun onItemClick(view: View, position: Int, item: FilterItem) {
                selectedItem = position
                Toast.makeText(this@MainActivity, "data = $item", Toast.LENGTH_SHORT).show()
                dismissPopup()
            }
        })

        return PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}
