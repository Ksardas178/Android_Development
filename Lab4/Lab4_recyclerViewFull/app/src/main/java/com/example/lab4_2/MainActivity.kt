package com.example.lab4_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view.adapter = Adapter(resources.openRawResource(R.raw.articles))
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }
}

//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.example.lab4_2.databinding.ActivityMainBinding

//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val exampleList = generateList(200)
//        val binding = ActivityMainBinding.inflate(layoutInflater)
//        val recyclerView = binding.recyclerView
//
//
//
//        recyclerView.adapter = ExampleAdapter(exampleList)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.setHasFixedSize(true)
//
//    }
//
//    private fun generateList(size: Int): List<ExampleItem> {
//        val list = ArrayList<ExampleItem>()
//
//        for (i in 0 until size) {
//            //TODO
//            val item = ExampleItem("ex", "ex","ex", "ex")
//            list += item
//        }
//        return list
//    }
//}