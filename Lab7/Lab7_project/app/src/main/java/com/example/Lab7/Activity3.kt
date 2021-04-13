package com.example.Lab7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.Lab7.databinding.Activity3Binding

class Activity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = Activity3Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}