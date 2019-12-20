package com.example.bowling_kata

class Game {
    private var score: Int = 0
    private var frames = ArrayList<frame>(10)

    init {
        resetScore()
        resetFrames()
    }


    fun rolls(pins: Int) {
        score += pins
    }


    fun score(): Int {
        return score
    }

    fun frames(): ArrayList<frame> {
        return frames
    }


    fun resetScore() {
        score = 0
    }

    fun resetFrames() {
        frames = ArrayList(10)
    }
}

data class frame(val turn: Int, val roll_one: Int, val roll_two: Int, val roll_three: Int?)