package com.example.edgeofumpire.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val firstPlayerScore: GameScore,
    val secondPlayerScore: GameScore
) {
    fun getWinner() : Winner {
        if (firstPlayerScore == GameScore.Won) {
            return Winner.Player1
        }
        if (secondPlayerScore == GameScore.Won) {
            return Winner.Player2
        }
        return Winner.None
    }

    fun increaseFirstPlayerScore() : Game {
        return this.copy(firstPlayerScore= firstPlayerScore.increase())
    }

    fun increaseSecondPlayerScore() : Game {
        return this.copy(secondPlayerScore= secondPlayerScore.increase())
    }

    companion object {
        fun newGame() : Game = Game(
            firstPlayerScore = GameScore.Zero,
            secondPlayerScore = GameScore.Zero
        )
    }
}
