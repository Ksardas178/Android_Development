package com.example.lab3_3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3_3.databinding.Activity3Binding

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val logTag = "ThirdActivity"
        Log.e(logTag, "third activity created")
        super.onCreate(savedInstanceState)

        val binding = Activity3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toFirstButton3.setOnClickListener {
            Log.e(logTag, "to first button clicked")
            val intent = Intent(this, FirstActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        binding.toSecondButton3.setOnClickListener {
            Log.e(logTag, "to second button clicked")
            val intent = Intent(this, SecondActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
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