package com.example.bowling_kata

class Game {
    private var score: Int = 0

    init {
        resetScore()
    }


    fun rolls(pins: Int) {
        score += pins
    }


    fun score(): Int {
        return score
    }

    fun resetScore() {
        score = 0
    }
}