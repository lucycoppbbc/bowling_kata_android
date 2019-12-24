package com.example.bowling_kata.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bowling_kata.R
import com.example.bowling_kata.controller.BowlingGameController
import com.example.bowling_kata.view.BowlingGameView
import com.example.bowling_kata.viewmodel.BowlingGameViewModel

class BowlingGameFragment : Fragment() {
    private var controller: BowlingGameController? = null
    private lateinit var bowlingGameView: BowlingGameView
    private var viewModel: BowlingGameViewModel = BowlingGameViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bowling_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bowlingGameView = createBowlingGameView(view)
        createBowlingGameController()

    }

    private fun createBowlingGameView(fragmentView: View): BowlingGameView {
        return BowlingGameView(fragmentView)
    }

    private fun createBowlingGameController() {
        controller = BowlingGameController(viewModel, bowlingGameView)
    }
}