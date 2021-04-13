package com.example.Lab7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.Lab7.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}