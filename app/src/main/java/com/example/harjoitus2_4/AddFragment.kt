package com.example.harjoitus2_4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import com.example.harjoitus2_4.databinding.FragmentAddBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddFragment : Fragment() {

    lateinit var binding: FragmentAddBinding
    private var vehicle: String = ""
    private var selectedDateInMillis: Long = 0
    private var kilometers: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_startFragment)
        }

        val defaultTimeInMillis = System.currentTimeMillis()
        updateSelectedDate(defaultTimeInMillis)

        val arrayAdapter: ArrayAdapter<CharSequence> =
            ArrayAdapter.createFromResource(requireContext(), R.array.company_cars, android.R.layout.simple_spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        binding.spinner.adapter = arrayAdapter

        binding.chooseDateButton.setOnClickListener {
            showDatePicker()
            selectedDateInMillis
        }

        binding.editTextNumber

        binding.saveButton.setOnClickListener {
            vehicle = binding.spinner.selectedItem.toString()
            kilometers = binding.editTextNumber.text.toString().toIntOrNull() ?: 0


            val saveDrive = AddFragmentDirections.actionAddFragmentToSavedFragment(
                vehicle,
                selectedDateInMillis,
                kilometers)

            findNavController().navigate(saveDrive)
        }

        return binding.root
    }

    private fun showDatePicker() {
        val builder = MaterialDatePicker.Builder.datePicker()
        val picker = builder.build()

        picker.addOnPositiveButtonClickListener(MaterialPickerOnPositiveButtonClickListener { selection ->
            val selectedDateInMillis = selection

            updateSelectedDate(selectedDateInMillis)
        })

        picker.show(parentFragmentManager, picker.toString())
    }

    private fun updateSelectedDate(selectedDateInMillis: Long) {
        this.selectedDateInMillis = selectedDateInMillis
        val sdf = SimpleDateFormat("dd.MM.yyy", Locale.getDefault())
        val selectedDate = sdf.format(Date(selectedDateInMillis))

        binding.dateTextView.setText(selectedDate)
    }
}