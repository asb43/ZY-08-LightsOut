package com.zybooks.lightsout

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.zybooks.lightsout.databinding.FragmentColorBinding

class ColorFragment : Fragment() {
    private lateinit var binding: FragmentColorBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentColorBinding.inflate(layoutInflater, container, false)

        // Extract color ID from SharedPreferences
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val colorId = sharedPref.getInt("color", R.color.yellow)

        // Select the radio button matching the color ID
        val radioButton = when (colorId) {
            R.color.red -> binding.radioRed
            R.color.orange -> binding.radioOrange
            R.color.green -> binding.radioGreen
            else -> binding.radioYellow
        }

        //val radioButton = rootView.findViewById<RadioButton>(radioId)
        radioButton.isChecked = true

        // Add click callback to all radio buttons
        //val colorRadioGroup = rootView.findViewById<RadioGroup>(R.id.color_radio_group)
        for (radioBtn in binding.colorRadioGroup.children) {
            radioBtn.setOnClickListener(this::onColorSelected)
        }

        return binding.root
    }

    //TODO: Once the color is selected, use the parentFragmentManager to replace this fragment with the GameFragment
    private fun onColorSelected(view: View) {
        val colorId = when (view.id) {
            R.id.radio_red -> R.color.red
            R.id.radio_orange -> R.color.orange
            R.id.radio_green -> R.color.green
            else -> R.color.yellow
        }
        // Save selected color ID in SharedPreferences
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt("color", colorId)
        editor.apply()

    }
}