package com.example.harjoitus2_4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.harjoitus2_4.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    lateinit var binding: FragmentStartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(inflater, container, false)

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_addFragment)
        }

        return binding.root
    }
}