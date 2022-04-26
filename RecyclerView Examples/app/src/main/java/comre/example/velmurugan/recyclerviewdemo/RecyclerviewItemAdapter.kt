package comre.example.velmurugan.recyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import comre.example.velmurugan.recyclerviewdemo.RecyclerviewItemAdapter.MyViewHolder

class RecyclerviewItemAdapter internal constructor(private val itemsList: List<Items>) :
    RecyclerView.Adapter<MyViewHolder>() {
    private var clickListener: ClickListener<Items>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.name.text = item.name
        holder.price.text = item.price.toString()
        holder.itemLayout.setOnClickListener { v -> clickListener!!.onClick(v, item, position) }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    fun setOnItemClickListener(clickListener: ClickListener<Items>) {
        this.clickListener = clickListener
    }

    inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tvName)
        var price: TextView = itemView.findViewById(R.id.tvPrice)
        val itemLayout: LinearLayout = itemView.findViewById(R.id.itemLayout)

    }
}