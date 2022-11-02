package com.zybooks.lightsout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.zybooks.lightsout.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {
    private lateinit var binding: FragmentHelpBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentHelpBinding.inflate(layoutInflater, container, false)

        //TODO: When the light bulb image is clicked, use the parentFragmentManager to replace this fragment with the GameFragment
        binding.lightBulbImage.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<GameFragment>(R.id.fragment_container_view)

            }
        }
        return binding.root
    }

}