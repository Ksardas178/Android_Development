package com.example.lab7_2_reciever

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.lab7_2_reciever.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val filePathReceiver = FilePathReceiver()
    private val logTag = "Activity1"
    private val url = "https://sun9-28.userapi.com/impf/c855220/v855220189/a4d61/Axp4Dzl8Pp4.jpg?size=1280x960&quality=96&sign=6f80b554ec40d0ece02a2c3f1cebeda8&type=album"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val intentFilter = IntentFilter(BroadcastMessages.BROADCAST_IMAGE_LOCATION_SENT.message)
        Log.i(logTag, "Activity running in thread: " + Thread.currentThread().name)

        registerReceiver(filePathReceiver, intentFilter)

        setContentView(binding.root)

        val path = intent.getStringExtra("path")
        if (path != null) {
            binding.textView.text = path
        } else {
            binding.textView.text = "Image is not downloaded yet :("
        }
    }

    class FilePathReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val path = intent?.getStringExtra("imagePath")
            val activityIntent = Intent(context, MainActivity::class.java)
            activityIntent.putExtra("path", path)
            //We don't want a new activity to be created here in backstack
            activityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            context?.startActivity(activityIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(filePathReceiver)
    }
}