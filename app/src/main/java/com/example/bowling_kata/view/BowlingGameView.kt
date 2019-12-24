package com.example.bowling_kata.view;

import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_bowling_game.view.*

class BowlingGameView(view: View) {
    private val scoreTextView: TextView = view.score_textView
    private val turnTextView: TextView = view.turn_textView
    private val randomNumberButton: Button = view.random_number_button
    private val strikeButton: Button = view.strike_button
    private val spareButton: Button = view.spare_button

    fun setScoreTextView(score: Int) {
        scoreTextView.text = score.toString()
    }

    fun setTurnTextView(turn: Int) {
        turnTextView.text = turn.toString()
    }

    fun setRandomNumberButtonOnClick(onClick: () -> Unit) {
        randomNumberButton.setOnClickListener { onClick() }
    }

    fun setStrikeButtonOnClick(onClick: () -> Unit) {
        strikeButton.setOnClickListener { onClick() }
    }

    fun setSpareButtonOnClick(onClick: () -> Unit) {
        spareButton.setOnClickListener { onClick() }
    }

}