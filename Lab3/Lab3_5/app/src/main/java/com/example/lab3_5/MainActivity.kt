package com.example.lab3_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.example.lab3_5.databinding.MainActivityBinding
import com.example.lab3_5.databinding.Fragment1Binding
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private val logTag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val binding = MainActivityBinding.inflate(layoutInflater)
        val bottomNav = binding.bottomNav

        setContentView(binding.root)

        Log.e(logTag, "first activity created")

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.activity_about_dest -> {

                    Log.e(logTag, "navigation button clicked")

                    bottomNav.visibility = View.INVISIBLE
                    val navController = findNavController(binding.fragment1.id)
                    navController.navigate(R.id.fragment_About)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.findViewById<View>(R.id.bottom_nav).visibility = View.VISIBLE
    }
}