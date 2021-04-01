package com.example.lab3_3

//import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3_3.databinding.ActivityAboutBinding

class ActivityAbout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val logTag = "ActivityAbout"
        Log.e(logTag, "Activity about created")
        super.onCreate(savedInstanceState)

        val binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}