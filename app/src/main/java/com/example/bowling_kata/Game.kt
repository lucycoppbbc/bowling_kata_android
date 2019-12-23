package com.example.bowling_kata

import android.util.Log

class Game {
    private var score: Int = 0
    private lateinit var frames: Array<Frame>
    private val numberOfTurns = 10
    private var currentTurn = 1

    init {
        resetScore()
        resetFrames()
    }


    fun rolls(pins: Int) {
        score += pins
        updateFrame(pins, currentTurn)

    }

    fun updateFrame(pins: Int, turn: Int) {
        val currentFrame = getCurrentFrame(turn)
        when {
            currentFrame.roll_one == null -> currentFrame.roll_one = pins
            currentFrame.roll_two == null -> {
                currentFrame.roll_two = pins
                if (turn < numberOfTurns) currentTurn++
            }
            currentFrame.roll_three == null && turn == numberOfTurns -> currentFrame.roll_three = pins
        }
    }

    fun score(): Int {
        return score
    }

    fun getFrames(): Array<Frame> {
        return frames
    }

    fun getCurrentFrame(turn: Int): Frame {
        //TODO find a nicer way of doing this
        return frames.filter { it.turn == turn }[0]
    }


    fun resetScore() {
        score = 0
    }
    
    fun makeFrame(turn: Int): Frame {
        return Frame(turn, null, null, null)
    }

    fun resetFrames() {
        frames = Array(numberOfTurns) { makeFrame(it+1) }

    }
}

data class Frame(var turn: Int, var roll_one: Int?, var roll_two: Int?, var roll_three: Int?) {
}