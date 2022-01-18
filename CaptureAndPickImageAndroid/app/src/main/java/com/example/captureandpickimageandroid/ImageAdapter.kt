package com.example.captureandpickimageandroid

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ImageAdapter : RecyclerView.Adapter<ImageViewHolder>() {

    var selectedImagePath = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_image_adapter, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imagePath = selectedImagePath[position]
        holder.image.setImageBitmap(BitmapFactory.decodeFile(imagePath))
        holder.filename.text = imagePath.split("/").last().toString()
    }

    override fun getItemCount(): Int {
        return selectedImagePath.size
    }

    fun addSelectedImages(images: List<String>) {
        this.selectedImagePath = images
        notifyDataSetChanged()
    }
}

class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val image: ImageView = view.findViewById(R.id.imgSelected)
    val filename: TextView = view.findViewById(R.id.tvSelected)

}
