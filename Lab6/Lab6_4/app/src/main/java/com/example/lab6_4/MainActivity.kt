package com.example.lab6_4

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6_4.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

private const val logTag = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val url = "https://sun9-28.userapi.com/impf/c855220/v855220189/a4d61/Axp4Dzl8Pp4.jpg?size=1280x960&quality=96&sign=6f80b554ec40d0ece02a2c3f1cebeda8&type=album"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        Log.i(logTag, "MainActivity created")

        binding.button.setOnClickListener {
            Log.i(logTag, "Clicked")
            Picasso.get().load(url).into(binding.image)
        }

        setContentView(binding.root)
    }
}

