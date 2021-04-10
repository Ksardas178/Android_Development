package com.example.lab5_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab5_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var taps = 0

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.button.setOnClickListener {
            taps++
            binding.button.text = getString(R.string.button_text_common, taps)
        }
        setContentView(binding.root)
    }
}
