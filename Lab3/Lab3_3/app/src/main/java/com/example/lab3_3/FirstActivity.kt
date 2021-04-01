package com.example.lab3_3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lab3_3.databinding.Activity1Binding

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val logTag = "FirstActivity"
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
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        val bottomNav = binding.bottomNav

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.activity_about_dest -> {
                    Log.e(logTag, "navigation button clicked")
                    val intent = Intent(this, ActivityAbout::class.java)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}