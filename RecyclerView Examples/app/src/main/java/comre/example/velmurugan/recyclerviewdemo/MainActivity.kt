package comre.example.velmurugan.recyclerviewdemo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var recyclerviewItemAdapter: RecyclerviewItemAdapter? = null
    private var itemsList = mutableListOf<Items>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemsList = ArrayList()
        recyclerView = findViewById<View>(R.id.recycleView) as RecyclerView
        recyclerviewItemAdapter = RecyclerviewItemAdapter(itemsList)
        recyclerView!!.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = recyclerviewItemAdapter
        recyclerviewItemAdapter!!.setOnItemClickListener(object : ClickListener<Items> {
            override fun onClick(view: View?, data: Items, position: Int) {
                Toast.makeText(
                    applicationContext, """Position = $position
 Item = ${data.name}""", Toast.LENGTH_SHORT
                ).show()
            }
        })
        prepareItems()
    }

    private fun prepareItems() {
        for (i in 0..49) {
            val items = Items("Item$i", 20 + i)
            itemsList!!.add(items)
        }
        recyclerviewItemAdapter!!.notifyDataSetChanged()
    }
}