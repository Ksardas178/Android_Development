package com.example.lab3_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3_2.databinding.Activity3Binding

class ThirdActivity : AppCompatActivity() {

    private val logTag = "ThirdActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(logTag, "third activity created")
        super.onCreate(savedInstanceState)

        val binding = Activity3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toFirstButton3.setOnClickListener {
            Log.e(logTag, "to first button clicked")
            setResult(Activity.RESULT_OK)
            finish()
        }

        binding.toSecondButton3.setOnClickListener {
            Log.e(logTag, "to second button clicked")
            finish()
        }
    }

    fun activityAboutClicked(item: MenuItem) {
        Log.e(logTag, "navigation button clicked")
        val intent = Intent(this, ActivityAbout::class.java)
        startActivity(intent)
    }
}