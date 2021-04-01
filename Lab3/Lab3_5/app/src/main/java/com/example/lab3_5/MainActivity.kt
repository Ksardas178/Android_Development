package com.example.lab3_5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.lab3_5.databinding.MainActivityBinding
import com.example.lab3_5.databinding.Fragment1Binding

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val logTag = "First Activity"
        val binding = MainActivityBinding.inflate(layoutInflater)
        val bottomNav = binding.bottomNav

        Log.e(logTag, "first activity created")

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.activity_about_dest -> {
                    Log.e(logTag, "navigation button clicked")
                    val intent = Intent(this, ActivityAbout::class.java)
                    startActivity(intent)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
}

class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //super.onCreate(savedInstanceState)

        val logTag = "FirstFragment"
        val binding = Fragment1Binding.inflate(layoutInflater)

        Log.e(logTag, "first fragment created")

        binding.toSecondButton1.setOnClickListener {
            Log.e(logTag, "to second button clicked")
            it.findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }



        return binding.root
    }
}