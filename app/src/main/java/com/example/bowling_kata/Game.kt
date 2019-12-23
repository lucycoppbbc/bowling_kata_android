package com.example.bowling_kata

import android.util.Log

class Game {
    private var score: Int = 0
    private lateinit var frames: Array<Frame>
    private val numberOfTurns = 10
    private var currentTurn = 1
    private var numberOfBonusRolls = 0


    init {
        resetScore()
        resetFrames()
    }


    fun rolls(pins: Int) {
        score += pins
        updateFrame(pins, currentTurn)

    }

    private fun updateNumberOfBonusRolls(bonus: Bonus) {
        when(bonus){
            Bonus.STRIKE -> numberOfBonusRolls += 2
            Bonus.SPARE -> numberOfBonusRolls += 1
        }
    }

    private fun addAnyBonusPoints(pins: Int) {
        if(numberOfBonusRolls > 0) {
            score += pins
            numberOfBonusRolls--
        }
    }

    private fun handleFirstRoll(frame: Frame, pins: Int) {
        frame.roll_one = pins
        if (pins == 10) {
            updateNumberOfBonusRolls(Bonus.STRIKE)
            if (currentTurn != numberOfTurns) currentTurn++
        }
    }

    private fun handleSecondRoll(frame: Frame, pins: Int) {
        frame.roll_two = pins
        frame.roll_one?.let {
            if(it + pins == 10) {
                updateNumberOfBonusRolls(Bonus.SPARE)
            }
        }
        if (currentTurn < numberOfTurns) currentTurn++


    }

    fun updateFrame(pins: Int, turn: Int) {
        val currentFrame = getCurrentFrame(turn)
        var frames = getFrames()
        addAnyBonusPoints(pins)
        when {
            currentFrame.roll_one == null -> handleFirstRoll(currentFrame, pins)
            currentFrame.roll_two == null -> handleSecondRoll(currentFrame, pins)
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

data class Frame(var turn: Int, var roll_one: Int?, var roll_two: Int?, var roll_three: Int?) {}
enum class Bonus { SPARE, STRIKE }