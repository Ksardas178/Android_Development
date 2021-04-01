package com.example.lab3_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3_4.databinding.Activity2Binding

class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val logTag = "SecondActivity"
        Log.e(logTag, "second activity created")
        super.onCreate(savedInstanceState)

        val binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toFirstButton2.setOnClickListener {
            Log.e(logTag, "to first button clicked")
            val intent = Intent(this, FirstActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        binding.toThirdButton2.setOnClickListener {
            Log.e(logTag, "to third button clicked")
            val intent = Intent(this, ThirdActivity::class.java)
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