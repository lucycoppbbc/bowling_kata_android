package com.example.bowling_kata

import org.junit.Test
import kotlin.test.assertEquals

class GameTest {


    @Test
    fun `score is reset on initialisation`() {
        val game = Game()
        assertEquals(game.score(), 0)
    }

    @Test
    fun `player scores no points`() {
        val game = Game()
        game.rolls(0)
        assertEquals(game.score(), 0)
    }

    @Test
    fun `player scores one point`() {
        val game = Game()
        game.rolls(1)
        assertEquals(game.score(), 1)
    }

    @Test
    fun `player scores one point in both rounds`()  {
        val game = Game()
        game.rolls(1)
        game.rolls(1)
        assertEquals(game.score(), 2)
    }

    @Test
    fun `players first score is added to current frame`() {
        val game = Game()
        game.rolls(1)
        val currentFrame = game.getCurrentFrame(1)
        assertEquals(currentFrame.roll_one, 1)
    }

    @Test
    fun `players second score is added to current frame`() {
        val game = Game()
        game.rolls(1)
        game.rolls(2)
        val currentFrame = game.getCurrentFrame(1)
        assertEquals(currentFrame.roll_two, 2)
    }

    @Test
    fun `players third score is not added when its not the final turn`() {
        val game = Game()
        game.rolls(1)
        game.rolls(2)
        game.rolls(3)
        val currentFrame = game.getCurrentFrame(1)
        assertEquals(currentFrame.roll_three, null)
    }

    @Test
    fun `score correct for simple game`() {
        val game = Game()
        rollBasicTurn(game, 10)
        assertEquals(20, game.score())
    }

    @Test
    fun `when a player gets a strike, the current turn moves on if its not the final frame`() {
        val game = Game()
        rollStrike(game)
        game.rolls(1)
        var firstFrame = game.getCurrentFrame(1)
        var secondFrame = game.getCurrentFrame(2)
        assertEquals(null, firstFrame.roll_two)
        assertEquals(1, secondFrame.roll_one)
    }

    @Test
    fun `when a player gets a strike, bonus is added of the next two rolls`() {
        val game = Game()
        rollStrike(game) //player gets strike
        rollBasicTurn(game, 1)
        assertEquals(14, game.score())
    }

    @Test
    fun `when a player gets two strikes in a row, score is as expected`() {
        val game = Game()
        rollStrike(game)
        rollStrike(game)
        rollBasicTurn(game, 2)
        assertEquals(37, game.score())

    }

    @Test
    fun `when a player gets three strikes in a row, score is as expected`() {
        val game = Game()
        rollStrike(game)
        rollStrike(game)
        rollStrike(game)
        rollBasicTurn(game, 3)
        assertEquals(60, game.score())
    }

    @Test
    fun `when a player gets a spare, bonus is added from the next roll`() {
        val game = Game()
        rollSpare(game)
        rollBasicTurn(game, 1)
        assertEquals(13, game.score())
    }

    @Test
    fun `when a player gets two spares, score is as expected`() {
        val game = Game()
        rollSpare(game)
        rollSpare(game)
        rollBasicTurn(game, 1)
        assertEquals(28, game.score())
    }

    @Test
    fun `when a player gets three spares, score is as expected`() {
        val game = Game()
        rollSpare(game)
        rollSpare(game)
        rollSpare(game)
        rollBasicTurn(game,1)
        assertEquals(43, game.score())
    }

    @Test
    fun `when a player gets a strike, then a spare, score is as expected`() {
        val game = Game()
        rollStrike(game)
        rollSpare(game)
        rollBasicTurn(game, 1)
        assertEquals(33, game.score())
    }

    @Test
    fun `when it is the final turn, and a strike is scored the player should get three bowls`() {
        val game = Game()
        rollBasicTurn(game, 9)
        rollStrike(game)
        game.rolls(1)
        assertEquals(false, game.isGameOver())
        game.rolls(1)
        assertEquals(true, game.isGameOver())
    }

    @Test
    fun `when it is the final turn, and a spare is scored the player should get three bowls`() {
        val game = Game()
        rollBasicTurn(game, 9)
        rollSpare(game)
        assertEquals(false, game.isGameOver())
        game.rolls(1)
        assertEquals(true, game.isGameOver())
    }

    @Test
    fun `when it is the final turn, and neither a strike or spare  is scored the player should get two bowls`() {
        val game = Game()
        rollBasicTurn(game, 9)
        game.rolls(1)
        game.rolls(2)
        assertEquals(true, game.isGameOver())
    }

    @Test
    fun `when scoring three strikes in final turn, score is as expected`() {
        val game = Game()
        for(x in 1..9) {
            game.rolls(0)
            game.rolls(0)
        }
        rollStrike(game)
        rollStrike(game)
        rollStrike(game)
        //score should be 10 + 20 + 20
        assertEquals(50, game.score())
    }

    @Test
    fun `when scoring a spare in final turn, score is as expected`(){
        val game = Game()
        for(x in 1..9) {
            game.rolls(0)
            game.rolls(0)
        }
        rollSpare(game)
        game.rolls(3)
        //score should be 5 + 5 + 6
        assertEquals(16, game.score())
    }

    @Test
    fun `when not scoring a spare or strike, score is as expected`() {
        val game = Game()
        for(x in 1..9) {
            game.rolls(0)
            game.rolls(0)
        }
        game.rolls(1)
        game.rolls(2)
        game.rolls(3)
        //score should be 1 + 2
        assertEquals(3, game.score())
    }

    private fun rollBasicTurn(game: Game, numberOfTurns: Int) {
        for(x in 1..numberOfTurns) {
            game.rolls(1)
            game.rolls(1)
        }
    }


    private fun rollStrike(game: Game) {
        game.rolls(10)
    }

    private fun rollSpare(game: Game) {
        game.rolls(5)
        game.rolls(5)
    }







}