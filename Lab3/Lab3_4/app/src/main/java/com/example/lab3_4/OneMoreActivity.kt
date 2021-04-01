package com.example.lab3_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3_4.databinding.ActivityMoreBinding

class OneMoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val logTag = "MoreActivity"
        Log.e(logTag, "One more activity created")
        super.onCreate(savedInstanceState)

        val binding = ActivityMoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toMoreButton.setOnClickListener {
            Log.e(logTag, "to more button clicked")
            val intent = Intent(this, OneMoreActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }
}