package com.example.lab3_2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.lab3_2.databinding.Activity2Binding

const val THIRD_ACTIVITY_REQUEST_CODE = 3

class SecondActivity: AppCompatActivity() {

    private val logTag = "SecondActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(logTag, "second activity created")
        super.onCreate(savedInstanceState)

        val binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toFirstButton2.setOnClickListener {
            Log.e(logTag, "to first button clicked")
            finish()
        }

        binding.toThirdButton2.setOnClickListener {
            Log.e(logTag, "to third button clicked")
            val intent = Intent(this, ThirdActivity::class.java)
            startActivityForResult(intent, THIRD_ACTIVITY_REQUEST_CODE)
        }
    }

    fun activityAboutClicked(item: MenuItem) {
        Log.e(logTag, "navigation button clicked")
        val intent = Intent(this, ActivityAbout::class.java)
        startActivity(intent)
    }

    // This method is called when the third activity finishes
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Check that it is the ThirdActivity with an OK result
        if (requestCode == THIRD_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                finish()
            }
        }
    }
}
