package com.zybooks.lightsout

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.zybooks.lightsout.databinding.FragmentGameBinding

const val GAME_STATE = "gameState"

class GameFragment : Fragment() {
    private lateinit var binding:FragmentGameBinding
    private lateinit var game: LightsOutGame
    private lateinit var lightGridLayout: GridLayout
    private var lightOnColor = 0
    private var lightOffColor = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(layoutInflater,container,false)

        // Add the same click handler to all grid buttons
        lightGridLayout = binding.lightGrid
        for (gridButton in lightGridLayout.children) {
            gridButton.setOnClickListener(this::onLightButtonClick)
        }

        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val onColorId = sharedPref.getInt("color", R.color.yellow)

        lightOnColor = ContextCompat.getColor(requireActivity(), onColorId)
        lightOffColor = ContextCompat.getColor(requireActivity(), R.color.black)

        game = LightsOutGame()

        if (savedInstanceState == null) {
            startGame()
        } else {
            game.state = savedInstanceState.getString(GAME_STATE)!!
            setButtonColors()
        }

        binding.newGameButton.setOnClickListener { startGame() }

        //TODO: When the chooseColorButton is clicked (setOnClickListener), use the parentFragmentMana ger toreplace this fragment with the ColorFragment
        binding.chooseColorButton.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ColorFragment>(R.id.fragment_container_view)
            }
        }
        //TODO: When the helpButton is clicked, use the parentFragmentManager to replace this fragment with HelpFragment
        binding.helpButton.setOnClickListener {
            parentFragmentManager.commit {
                setReorderingAllowed(true)
                replace<HelpFragment>(R.id.fragment_container_view)

             }
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(GAME_STATE, game.state)
    }

    private fun startGame() {
        game.newGame()
        setButtonColors()
    }

    private fun onLightButtonClick(view: View) {

        // Find the button's row and col
        val buttonIndex = lightGridLayout.indexOfChild(view)
        val row = buttonIndex / GRID_SIZE
        val col = buttonIndex % GRID_SIZE

        game.selectLight(row, col)
        setButtonColors()

        // Congratulate the user if the game is over
        if (game.isGameOver) {
            Toast.makeText(requireActivity(), R.string.congrats, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setButtonColors() {

        // Set all buttons' background color
        for (buttonIndex in 0 until lightGridLayout.childCount) {
            val gridButton = lightGridLayout.getChildAt(buttonIndex)

            // Find the button's row and col
            val row = buttonIndex / GRID_SIZE
            val col = buttonIndex % GRID_SIZE

            if (game.isLightOn(row, col)) {
                gridButton.setBackgroundColor(lightOnColor)
            } else {
                gridButton.setBackgroundColor(lightOffColor)
            }
        }
    }
}