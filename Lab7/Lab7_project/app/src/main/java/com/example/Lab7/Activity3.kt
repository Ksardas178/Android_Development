package com.example.Lab7

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.Lab7.databinding.Activity3Binding
import kotlinx.android.synthetic.main.activity_1.*

private const val logTag = "Activity3"

class Activity3 : AppCompatActivity() {

    private lateinit var messenger: Messenger
    private var bound = false
    private val url = "https://sun9-28.userapi.com/impf/c855220/v855220189/a4d61/Axp4Dzl8Pp4.jpg?size=1280x960&quality=96&sign=6f80b554ec40d0ece02a2c3f1cebeda8&type=album"

    companion object {
        var pathText : String? = null
    }

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.i(logTag, "Connection established")
            messenger = Messenger(service)
            bound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i(logTag, "Service disconnected")
            bound = false
        }
    }

    class OncomingHandler() : Handler() {
        override fun handleMessage(msg: Message) {
            Log.i(logTag, "Client handling a message...")
            super.handleMessage(msg)
            when (msg.what) {
                MessageFlags.FLAG_RESPONSE_TO_SENDER.value -> {
                    Log.i(logTag, "Reply message received")
                    pathText = msg.data.getString("imagePath")
                }
                else -> super.handleMessage(msg)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = Activity3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            Log.i(logTag, "Clicked")
            sendMessage(binding)
        }
        setContentView(binding.root)
    }

    private fun sendMessage(binding: Activity3Binding) {
        if (!bound) {
            Log.i(logTag, "Binding service")
            binding.button.text = getString(R.string.download_image_text)
            val serviceIntent = Intent(this, BoundService::class.java)
            bindService(serviceIntent, connection, Context.BIND_AUTO_CREATE)
        } else {
            Log.i(logTag, "Requesting service")

            sendRequest()

            if (pathText != null) binding.apply{
                textView.text = pathText
                button.text = getString(R.string.path_is_displayed)
            }
        }
    }

    private fun sendRequest() {
        //Create and send a message to the service
        val bundle = Bundle()
        bundle.putString("url", url)

        val responseMessenger = Messenger(OncomingHandler())
        val msg = Message.obtain().apply {
            what = MessageFlags.FLAG_FOR_BOUND_SERVICE_INTERACTION.value
            replyTo = responseMessenger
            data = bundle
        }
        messenger.send(msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
        bound = false
    }
}