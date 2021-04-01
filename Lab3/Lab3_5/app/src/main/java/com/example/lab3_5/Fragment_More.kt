package com.example.lab3_5

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lab3_5.databinding.FragmentMoreBinding
import kotlin.math.log

class Fragment_More : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val logTag = "MoreActivity"
        val binding = FragmentMoreBinding.inflate(layoutInflater)

        Log.e(logTag, "More activity created")

        binding.toMoreButton.setOnClickListener {
            //As we want it to stop making new tasks
            //we can leave it as it is
            //it.findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }

        return binding.root
    }
}