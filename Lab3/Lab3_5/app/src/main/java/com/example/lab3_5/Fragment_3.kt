package com.example.lab3_5

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.lab3_5.databinding.Fragment3Binding

class Fragment_3 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val logTag = "ThirdFragment"
        val binding = Fragment3Binding.inflate(layoutInflater)

        Log.e(logTag, "third fragment created")

        binding.toFirstButton3.setOnClickListener {
            Log.e(logTag, "to first button clicked")
            it.findNavController().navigate(R.id.action_thirdFragment_to_firstFragment)
        }

        binding.toSecondButton3.setOnClickListener {
            Log.e(logTag, "to second button clicked")
            it.findNavController().navigate(R.id.action_thirdFragment_to_secondFragment)
        }

        binding.toMoreButton3.setOnClickListener {
            Log.e(logTag, "to more button clicked")
            it.findNavController().navigate(R.id.action_thirdFragment_to_fragment_More)
        }

        return binding.root
    }
}