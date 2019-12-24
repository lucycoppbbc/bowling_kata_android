package com.example.bowling_kata.controller

import com.example.bowling_kata.view.BowlingGameView
import com.example.bowling_kata.viewmodel.BowlingGameViewModel

class BowlingGameController(
    private val viewModel: BowlingGameViewModel,
    private val view: BowlingGameView) {}