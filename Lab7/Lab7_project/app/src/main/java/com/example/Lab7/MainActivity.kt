package com.example.Lab7

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.Lab7.databinding.MainActivityBinding

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun task1ButtonClicked(view: View) {
        val intent = Intent(this, Activity1::class.java)
        startActivity(intent)
    }

    fun task2ButtonClicked(view: View) {
        val intent = Intent(this, Activity2::class.java)
        startActivity(intent)
    }

    fun task3ButtonClicked(view: View) {
        val intent = Intent(this, Activity3::class.java)
        startActivity(intent)
    }

}