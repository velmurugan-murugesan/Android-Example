package com.velmurugan.autocompleteexample

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.velmurugan.autocompleteexample.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var autoSuggestAdapter: AutoSuggestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Setting up the adapter for AutoSuggest
        //Setting up the adapter for AutoSuggest
        autoSuggestAdapter = AutoSuggestAdapter(this, R.layout.simple_dropdown_item_1line)
        binding.autoComplete.setThreshold(2)
        binding.autoComplete.setAdapter(autoSuggestAdapter)
        binding.autoComplete.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, autoSuggestAdapter.getObject(position), Toast.LENGTH_SHORT).show()
        }
        binding.autoComplete.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                    val count = binding.autoComplete.text.toString().length

                    if (count> 2) {
                        doApiCall()
                    }


            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    fun doApiCall() {
        val count = binding.autoComplete.text.toString().length
        CoroutineScope(Dispatchers.IO).launch {
            val apiCall = RetrofitService.getInstance().getAllMovies()
            if (apiCall.isSuccessful) {
                withContext(Dispatchers.Main) {
                    val response = apiCall.body()
                    updateAutoComplete(apiCall.body()?.subList(0,count))
                }
            }
        }
    }

    private fun updateAutoComplete(body: List<Movie>?) {
        body?.let {
            val list = it.map { it.name }
            autoSuggestAdapter.setData(list);
            autoSuggestAdapter.notifyDataSetChanged();
        }
    }
}