package com.example.lab3_5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.lab3_5.databinding.FragmentAboutBinding

class Fragment_About : Fragment() {

    private val logTag = "FragmentAbout"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = FragmentAboutBinding.inflate(layoutInflater)
        Log.e(logTag, "fragment about created")
        return binding.root
    }
}