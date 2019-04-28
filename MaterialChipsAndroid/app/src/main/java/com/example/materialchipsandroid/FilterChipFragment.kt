package com.example.materialchipsandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_filter_chip.*

class FilterChipFragment : Fragment() {

    var adapter: FilterChipListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_filter_chip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        chip_group_filter.setOnCheckedChangeListener { group, checkedId ->

            val chip: Chip? = group.findViewById(checkedId)

            chip?.let {chipView ->
                val predicate: (Items) -> Boolean = {item->
                    item.category == chipView.text
                }
                val filter = prepareData().filter(predicate)
                adapter?.setData(filter)
            } ?: kotlin.run {
                adapter?.setData(prepareData())
            }
        }

        adapter = FilterChipListAdapter()
        recyclerview_filter.layoutManager = LinearLayoutManager(context)
        recyclerview_filter.adapter = adapter
        adapter?.setData(prepareData())

    }

    fun updateData(view: View){
        val chip = view as Chip

        val predicate: (Items) -> Boolean = {
            it.category == chip.text
        }
        val filter = prepareData().filter(predicate)
        adapter?.setData(filter)
    }

    fun prepareData(): MutableList<Items> {
        val data = mutableListOf<Items>()
        data.add(Items("Food 1","Fast Delivery"))
        data.add(Items("Food 2","Pickup"))
        data.add(Items("Food 3","Best Offer"))
        data.add(Items("Food 4","Fast Selling"))
        data.add(Items("Food 5","Fast Delivery"))
        data.add(Items("Food 6","Pickup"))
        data.add(Items("Food 7","Fast Delivery"))
        data.add(Items("Food 8","Pickup"))
        return data
    }

    companion object {

        @JvmStatic
        fun newInstance() = FilterChipFragment()
    }

}