package com.example.Lab7

import android.app.IntentService
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.NullPointerException
import java.net.URL

class StartedService : IntentService("StartedService") {

    private val logTag = "StartedService"

    override fun onHandleIntent(intent: Intent?) {
        Log.i(logTag, "IntentService running in thread: " + Thread.currentThread().name)
        val url = intent?.getStringExtra("url")
        if (url != null) {
            val path = downloadFile(url)
            val successIntent = Intent()
            successIntent.putExtra("imagePath", path)
            successIntent.action = BroadcastMessages.BROADCAST_IMAGE_LOCATION_SENT.message
            sendBroadcast(successIntent)
        } else {
            throw NullPointerException("Wrong URL recieved")
        }
        stopSelf()
    }

    private fun downloadFile(url: String): String? {
        val image: Bitmap
        try {
            val input = URL(url).openStream()
            image = BitmapFactory.decodeStream(input)
            return saveFile(image)
        } catch (e: IOException) {
            Log.e(logTag, "IOException while loading image")
            return null
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