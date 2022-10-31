package com.zybooks.lightsout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.zybooks.lightsout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //This code uses a Fragment Manager to add the GameFragment to the fragment_container_view
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<GameFragment>(R.id.fragment_container_view)
        }
    }
}