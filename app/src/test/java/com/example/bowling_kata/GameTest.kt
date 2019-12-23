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






}