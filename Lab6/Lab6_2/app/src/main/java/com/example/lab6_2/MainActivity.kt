package com.example.lab6_2

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.os.FileUtils.copy
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6_2.databinding.ActivityMainBinding
import java.io.*
import java.lang.NullPointerException
import java.net.URL

//private const val IO_BUFFER_SIZE = 1024
private const val logTag = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val url = "https://lh3.googleusercontent.com/79K-QR-67TmYKuGljBtElzeFQoJFvMEHPkczJCisQhdNp3QNo0GQkWkENqH9FPlmob5FkwD9hVQMCZAP5MW65OTCA-UoechVs5ZpZkR4JuYtdoXc7xOSOdTdGRTtxPEJrEVckKOJ-1lgJ_Ck8N5O30fe1o5wTIAAHsTARek8-zf_VwDOtPuLZdGGcLJYJE48RHUslsWG46vXVP3FSaEXhsn0F4KCcidns5cbP4IaMJYxSDk9wa8ktwdeHYVnc8eMhjHYzTFyCGNRO1ySwY_11FHpos5uM3R_Tc0ooi3aZvyNvl6p1seD-YYfZA3oyUjh27SoZv6CW4exGq61K4lZSLR4-MwY7SznVvNSYt80Ovu-377g31BMWbnZKKeiHb2oIEhb1gyDfq4LGe2_6I57tDyple8VvGkMveXgM_obn_cDKyGMx48uhUqb-Ka7CmK1DfeS02rF54EKxs16nPM4EELg-7spmgvX-YawLJTiYYg8Njj7jWbanFVV5qne0100bADoX-gag8IWS0tnEB1jeXucGgZsd_rsN83Ie1OtwtPlbCnOxXzkKC4HrSdtv6hgBf-vy7BriIwCK9UGbjtRehUxVatjh031bC0FyQYGhRNWlExP3IyClD3mcyqZ5LlWL7YaPhPR9RIYn_pThDBhM__PGTv9GvhVwlvOVmgmPOa6NcUa1FBobnl_xsaMdZJG2I9fW27o1sfysSwwLV7YJ_Od=w876-h548-no?authuser=0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        Log.i(logTag, "MainActivity created")

        binding.button.setOnClickListener {
            Log.i(logTag, "Clicked")
            val downloadImage = DownloadImage(binding)
            downloadImage.execute(url)
        }

        setContentView(binding.root)
    }

    class DownloadImage(private val binding: ActivityMainBinding) : AsyncTask<String, Void, Bitmap>() {

        override fun doInBackground(vararg params: String): Bitmap {
            return loadBitmap(params.first())
        }

        override fun onPostExecute(result: Bitmap) {
            super.onPostExecute(result)
            binding.image.setImageBitmap(result)
            binding.button.text = "Completed"
            Log.i(logTag, "Download completed")
        }

        override fun onPreExecute() {
            super.onPreExecute()
            binding.button.text = "Downloading..."
            Log.i(logTag, "Downloading started...")
        }

        private fun loadBitmap(url: String): Bitmap {
            var bitmap: Bitmap? = null
            var `in`: InputStream? = null
            var out: BufferedOutputStream? = null
            try {
                `in` = BufferedInputStream(URL(url).openStream(), )
                val dataStream = ByteArrayOutputStream()
                out = BufferedOutputStream(dataStream)
                copy(`in`, out)
                out.flush()
                val data: ByteArray = dataStream.toByteArray()
                val options = BitmapFactory.Options()
                options.inSampleSize = 1
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.size, options)
            } catch (e: IOException) {
                Log.e(logTag, "Could not load Bitmap from: $url")
            } finally {
                `in`?.close()
                out?.close()
            }
            if (bitmap == null) {
                throw NullPointerException("Image not downloaded")
            } else {
                return bitmap
            }
        }
    }
}
