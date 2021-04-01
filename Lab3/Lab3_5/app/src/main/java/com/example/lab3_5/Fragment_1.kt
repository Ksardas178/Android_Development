package com.example.lab3_5

import android.os.Bundle
import android.service.voice.VoiceInteractionService
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
    import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.lab3_5.databinding.Fragment1Binding
import kotlin.properties.Delegates

class Fragment_1  : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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