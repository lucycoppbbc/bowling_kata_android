package com.example.bowling_kata


class Game {
    private var score: Int = 0
    private lateinit var frames: Array<Frame>
    private val numberOfTurns = 10
    private var currentTurn = 1
    private var numberOfBonusRolls = 0
    private var gameOver = false

    init {
        resetScore()
        resetFrames()
    }


    fun rolls(pins: Int) {
        if(!isGameOver()) {
            score += pins
            updateFrame(pins, currentTurn)
        }

    }

    fun isGameOver(): Boolean {
        return gameOver
    }

    private fun updateNumberOfBonusRolls(bonus: Bonus) {
        numberOfBonusRolls += bonus.numberOfBonusRolls
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
        val firstRoll = frame.roll_one ?: 0
        val spare = firstRoll + pins == 10
        if(currentTurn == numberOfTurns) {
            val strike = pins == 10
            val noStrikeScored = firstRoll != 10
            when {
                spare -> updateNumberOfBonusRolls(Bonus.SPARE)
                strike -> updateNumberOfBonusRolls(Bonus.STRIKE)
                noStrikeScored -> gameOver()
            }
        } else {
            if(spare) {
                updateNumberOfBonusRolls(Bonus.SPARE)
            }
            currentTurn++
        }
    }

    private fun handleThirdRoll(frame: Frame, pins: Int) {
        frame.roll_three = pins
        gameOver()

    }
    private fun gameOver() {
        gameOver = true
    }

    fun updateFrame(pins: Int, turn: Int) {
        val currentFrame = getCurrentFrame(turn)
        addAnyBonusPoints(pins)
        when {
            currentFrame.roll_one == null -> handleFirstRoll(currentFrame, pins)
            currentFrame.roll_two == null -> handleSecondRoll(currentFrame, pins)
            currentFrame.roll_three == null && turn == numberOfTurns -> handleThirdRoll(currentFrame, pins)
            else -> gameOver()
        }
    }

    fun score(): Int {
        return score
    }

    fun getCurrentFrame(turn: Int): Frame {
        return frames.filter { it.turn == turn }[0]
    }


    private fun resetScore() {
        score = 0
    }
    
    private fun makeFrame(turn: Int): Frame {
        return Frame(turn, null, null, null)
    }

    private fun resetFrames() {
        frames = Array(numberOfTurns) { makeFrame(it+1) }

    }
}

data class Frame(var turn: Int, var roll_one: Int?, var roll_two: Int?, var roll_three: Int?) {}
enum class Bonus(val numberOfBonusRolls: Int) { SPARE(1), STRIKE(2) }