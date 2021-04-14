package com.example.Lab7

import android.app.IntentService
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.*
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.NullPointerException
import java.net.URL

private const val logTag = "BoundService"

class BoundService : IntentService("BoundService") {

    override fun onCreate() {
        Log.i(logTag, "Bound service created")
        super.onCreate()
    }

    /** Handler for incoming messages from clients*/
    inner class IncomingHandler: Handler() {

        override fun handleMessage(msg: Message) {
            Log.i(logTag, "Service handling a message...")
            when (msg.what) {
                MessageFlags.FLAG_FOR_BOUND_SERVICE_INTERACTION.value -> {

                    val responseMessenger = msg.replyTo
                    val url = msg.data.getString("url")

                    CoroutineScope(Dispatchers.IO).launch {
                        Log.i(logTag, "BoundService running in thread: " + Thread.currentThread().name)
                        val path = downloadFile(url)

                        //Store image path into a bundle
                        val responseBundle = Bundle()
                        responseBundle.putString("imagePath", path)

                        val flag = MessageFlags.FLAG_RESPONSE_TO_SENDER.value
                        val responseMessage = Message.obtain().apply {
                            what = flag
                            data = responseBundle
                        }
                        responseMessenger.send(responseMessage)
                    }
                }
                else -> super.handleMessage(msg)
            }
        }
    }

   /**
    * When binding to the service, we return an
    * interface to our messenger for sending
    * messages to the service
    */
    override fun onBind(intent: Intent?): IBinder? {
        //Called only for the first client
        Log.i(logTag, "onBind called")
        val messenger = Messenger(IncomingHandler())
        return messenger.binder
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.i(logTag, "IntentService running in thread: " + Thread.currentThread().name)
        val url = intent?.getStringExtra("url")
        handleUrl(url)
        stopSelf()
    }

    private fun handleUrl(url: String?) {
            val path = downloadFile(url)
            val successIntent = Intent()
            successIntent.putExtra("imagePath", path)
            successIntent.action = BroadcastMessages.BROADCAST_IMAGE_LOCATION_SENT.message
            sendBroadcast(successIntent)
    }

    private fun downloadFile(url: String?): String? {
        Log.i(logTag, "Download file function called")
        if (url != null) {
            val image: Bitmap
            return try {
                val input = URL(url).openStream()
                image = BitmapFactory.decodeStream(input)
                saveFile(image)
            } catch (e: IOException) {
                Log.e(logTag, "IOException while loading image")
                null
            }
        } else {
            throw NullPointerException("Wrong URL received")
        }
    }

    private fun saveFile(image : Bitmap) : String {
        val fileContainer = getOutputMediaFile()
        val fos = FileOutputStream(fileContainer)
        image.compress(Bitmap.CompressFormat.PNG, 90, fos)
        fos.close()
        return fileContainer.absolutePath
    }

    private fun getOutputMediaFile() : File {
        //Путь до файлового хранилища
        val storage = File(filesDir.absolutePath)
        //Создаём директорию, если её не существует
        if (!storage.exists()) storage.mkdir()
        val fileName = "image.png"
        return File("${storage}/${fileName}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(logTag, "Service destroyed")
    }
}