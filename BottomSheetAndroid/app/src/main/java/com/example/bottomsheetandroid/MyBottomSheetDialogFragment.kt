package com.example.bottomsheetandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_modal_bottomsheet.*


class MyBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_modal_bottomsheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val items = mutableListOf<Item>()
        items.add(Item("Folder",R.drawable.ic_baseline_create_new_folder_24))
        items.add(Item("File",R.drawable.ic_baseline_insert_drive_file_24))
        items.add(Item("Photo",R.drawable.ic_baseline_add_a_photo_24))
        items.add(Item("Post",R.drawable.ic_baseline_post_add_24))
        items.add(Item("Poll",R.drawable.ic_baseline_poll_24))
        items.add(Item("Group",R.drawable.ic_baseline_group_add_24))
        items.add(Item("Alarm",R.drawable.ic_baseline_alarm_add_24))
        items.add(Item("Library",R.drawable.ic_baseline_library_add_24))


        recyclerview.layoutManager = GridLayoutManager(context, 3)
        val adapter = RecyclerviewAdapter(items)
        recyclerview.adapter = adapter

    }
}