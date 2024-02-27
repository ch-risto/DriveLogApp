package com.example.harjoitus2_4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.harjoitus2_4.databinding.FragmentSavedBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SavedFragment : Fragment() {

    lateinit var binding: FragmentSavedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSavedBinding.inflate(inflater, container, false)

        val vehicle = arguments?.getString("vehicle")
        val date = arguments?.getLong("date")
        val kilometers = arguments?.getInt("kilometers")

        binding.savedVehicleTextView.text = vehicle
        binding.savedDateTextView.text = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date(date ?: 0))
        binding.savedKilometersTextView.text = kilometers.toString()

        binding.returnMainButton.setOnClickListener {
            findNavController().navigate(R.id.action_savedFragment_to_startFragment)
        }


        return binding.root
    }
}