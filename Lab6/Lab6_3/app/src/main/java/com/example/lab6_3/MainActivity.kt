package com.example.lab6_3

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6_3.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.*
import java.net.URL

private const val logTag = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var job: Job
    private val url = "https://lh3.googleusercontent.com/79K-QR-67TmYKuGljBtElzeFQoJFvMEHPkczJCisQhdNp3QNo0GQkWkENqH9FPlmob5FkwD9hVQMCZAP5MW65OTCA-UoechVs5ZpZkR4JuYtdoXc7xOSOdTdGRTtxPEJrEVckKOJ-1lgJ_Ck8N5O30fe1o5wTIAAHsTARek8-zf_VwDOtPuLZdGGcLJYJE48RHUslsWG46vXVP3FSaEXhsn0F4KCcidns5cbP4IaMJYxSDk9wa8ktwdeHYVnc8eMhjHYzTFyCGNRO1ySwY_11FHpos5uM3R_Tc0ooi3aZvyNvl6p1seD-YYfZA3oyUjh27SoZv6CW4exGq61K4lZSLR4-MwY7SznVvNSYt80Ovu-377g31BMWbnZKKeiHb2oIEhb1gyDfq4LGe2_6I57tDyple8VvGkMveXgM_obn_cDKyGMx48uhUqb-Ka7CmK1DfeS02rF54EKxs16nPM4EELg-7spmgvX-YawLJTiYYg8Njj7jWbanFVV5qne0100bADoX-gag8IWS0tnEB1jeXucGgZsd_rsN83Ie1OtwtPlbCnOxXzkKC4HrSdtv6hgBf-vy7BriIwCK9UGbjtRehUxVatjh031bC0FyQYGhRNWlExP3IyClD3mcyqZ5LlWL7YaPhPR9RIYn_pThDBhM__PGTv9GvhVwlvOVmgmPOa6NcUa1FBobnl_xsaMdZJG2I9fW27o1sfysSwwLV7YJ_Od=w876-h548-no?authuser=0"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        Log.i(logTag, "MainActivity created")

        binding.button.setOnClickListener {
            Log.i(logTag, "Clicked")
            job = CoroutineScope(Dispatchers.Main).launch(Dispatchers.IO) {
                try {
                    val input = URL(url).openStream()
                    val image = BitmapFactory.decodeStream(input)
                    launch(Dispatchers.Main) {
                        binding.image.setImageBitmap(image)
                        binding.button.text = "Downloaded"
                    }
                } catch (e: IOException) {
                    Log.e(logTag, "IOException while loading image")
                }
            }
        }

        setContentView(binding.root)
    }
}