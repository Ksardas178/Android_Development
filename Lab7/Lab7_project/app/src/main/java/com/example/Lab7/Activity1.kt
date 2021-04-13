package com.example.Lab7

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.Lab7.databinding.Activity1Binding

class Activity1 : AppCompatActivity() {

    private val imageReceiver = ImageReceiver()
    private val logTag = "Activity1"
    private val url = "https://sun9-28.userapi.com/impf/c855220/v855220189/a4d61/Axp4Dzl8Pp4.jpg?size=1280x960&quality=96&sign=6f80b554ec40d0ece02a2c3f1cebeda8&type=album"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = Activity1Binding.inflate(layoutInflater)
        val intentFilter = IntentFilter(BroadcastMessages.BROADCAST_IMAGE_LOCATION_SENT.message)
        Log.i(logTag, "Activity running in thread: " + Thread.currentThread().name)

        registerReceiver(imageReceiver, intentFilter)

        binding.button.setOnClickListener {
            Log.i(logTag, "Clicked")
            val intent = Intent(this, StartedService::class.java)
            //pass params to intent
            intent.putExtra("url", url)
            startService(intent)
        }
        setContentView(binding.root)

        val path = intent.getStringExtra("path")
        if (path != null) {
            val bitmap = BitmapFactory.decodeFile(path)
            binding.image.setImageBitmap(bitmap)
        }
    }

    class ImageReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
        val path = intent?.getStringExtra("imagePath")
        val activityIntent = Intent(context, Activity1::class.java)
        activityIntent.putExtra("path", path)
        //We don't want a new activity to be created here in backstack
        activityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        context?.startActivity(activityIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(imageReceiver)
    }
}
