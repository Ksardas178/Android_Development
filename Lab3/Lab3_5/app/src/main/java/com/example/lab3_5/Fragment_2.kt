package com.example.lab3_5

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.lab3_5.databinding.Fragment2Binding

class Fragment_2 : Fragment() {

    private val logTag = "SecondFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = Fragment2Binding.inflate(layoutInflater)

        Log.e(logTag, "second fragment created")

        binding.toFirstButton2.setOnClickListener {
            Log.e(logTag, "to first button clicked")
            it.findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }

        binding.toThirdButton2.setOnClickListener {
            Log.e(logTag, "to third button clicked")
            it.findNavController().navigate(R.id.action_secondFragment_to_thirdFragment)
        }

        return binding.root
    }
}