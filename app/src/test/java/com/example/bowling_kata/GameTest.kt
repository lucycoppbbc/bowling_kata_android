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
    fun `players third score is added when its the final turn`() {
        val game = Game()
        playGame(game, 9)
        game.rolls(1) //first roll of final frame
        game.rolls(2) //second roll of final frame
        game.rolls(3) //third roll of final frame
        val currentFrame = game.getCurrentFrame(10)
        assertEquals(currentFrame.roll_three, 3)
    }

    @Test
    fun `score correct for simple game`() {
        val game = Game()
        playGame(game, 10)
        assertEquals(20, game.score())
    }

    @Test
    fun `when a player gets a strike, the current turn moves on if its not the final frame`() {
        val game = Game()
        game.rolls(10)
        game.rolls(1)
        var firstFrame = game.getCurrentFrame(1)
        var secondFrame = game.getCurrentFrame(2)
        assertEquals(null, firstFrame.roll_two)
        assertEquals(1, secondFrame.roll_one)
    }

    @Test
    fun `when a player gets a strike, bonus is added of the next two rolls`() {
        val game = Game()
        game.rolls(10) //player gets strike
        game.rolls(1)
        game.rolls(1)
        //bonus should be 2
        assertEquals(14, game.score())
    }

    @Test
    fun `when a player gets two strikes in a row, score is as expected`() {
        val game = Game()
        game.rolls(10)
        game.rolls(10)
        playGame(game, 2)
        //score should be 10 + 20 + 2 + 2 + 2 + 1
        assertEquals(37, game.score())

    }

    @Test
    fun `when a player gets three strikes in a row, score is as expected`() {
        val game = Game()
        game.rolls(10)
        game.rolls(10)
        game.rolls(10)
        playGame(game, 3)
        //score should be 10 + 20 + 20 + 2 + 2 + 2 + 2 + 1 + 1
        assertEquals(60, game.score())
    }

    fun playGame(game: Game, numberOfTurns: Int) {
        for(x in 1..numberOfTurns) {
            game.rolls(1)
            game.rolls(1)
        }
    }







}