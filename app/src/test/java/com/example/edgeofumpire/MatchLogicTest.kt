package com.example.edgeofumpire

import com.example.edgeofumpire.data.model.Match
import com.example.edgeofumpire.data.model.Winner
import junit.framework.TestCase.assertEquals
import org.junit.Test

class MatchLogicTest {

    @Test
    fun shouldBeAbleToHandleOneSidedMatchFirstPlayer() {
        var match = Match.startNewMatch("PLAYER 1", "PLAYER 2")

        while (match.getWinner() == Winner.None) {
            match = match.firstPlayerScored()
        }
        assertEquals(Winner.Player1, match.getWinner())
    }

    @Test
    fun shouldBeAbleToHandleOneSidedMatchSecondPlayer() {
        var match = Match.startNewMatch("PLAYER 1", "PLAYER 2")

        while (match.getWinner() == Winner.None) {
            match = match.secondPlayerScored()
        }
        assertEquals(Winner.Player2, match.getWinner())
    }
}