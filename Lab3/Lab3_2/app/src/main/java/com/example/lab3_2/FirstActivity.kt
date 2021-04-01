package com.example.lab3_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.lab3_2.databinding.Activity1Binding

class FirstActivity : AppCompatActivity() {

    private val logTag = "FirstActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(logTag, "first activity created")
        super.onCreate(savedInstanceState)

        val binding = Activity1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.setOnClickListener {
            Log.e(logTag, "textView clicked")
        }

        binding.toSecondButton1.setOnClickListener {
            Log.e(logTag, "to second button clicked")
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    fun activityAboutClicked(item: MenuItem) {
        Log.e(logTag, "navigation button clicked")
        val intent = Intent(this, ActivityAbout::class.java)
        startActivity(intent)
    }
}