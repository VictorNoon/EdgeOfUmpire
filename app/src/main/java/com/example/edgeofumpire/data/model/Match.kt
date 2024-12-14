package com.example.edgeofumpire.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

const val MATCH_TABLE = "match_table"
@Serializable
@Entity(tableName = MATCH_TABLE)
data class Match(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    val uid: Long,
    @ColumnInfo(name = "first_player_name")
    val firstPlayerName: String,
    @ColumnInfo(name = "second_player_name")
    val secondPlayerName: String,
    @ColumnInfo(name = "player_scores")
    val playerScores: List<Game>
) {
    fun firstPlayerScored() : Match {
        if (getWinner() != Winner.None) {
            return this
        }
        val newScore = playerScores.last().increaseFirstPlayerScore()
        val newPlayerScores = playerScores.toMutableList()
        newPlayerScores[newPlayerScores.lastIndex] = newScore
        if (newScore.getWinner() == Winner.Player1 &&
            playerScores.count { it.getWinner() == Winner.Player1 } < 5)
            newPlayerScores.add(Game.newGame())
        return this.copy(playerScores=newPlayerScores)
    }

    fun secondPlayerScored() : Match {
        if (getWinner() != Winner.None) {
            return this
        }
        val newScore = playerScores.last().increaseSecondPlayerScore()
        val newPlayerScores = playerScores.toMutableList()
        newPlayerScores[newPlayerScores.lastIndex] = newScore
        if (newScore.getWinner() == Winner.Player2 &&
            playerScores.count { it.getWinner() == Winner.Player2 } < 5)
            newPlayerScores.add(Game.newGame())
        return this.copy(playerScores=newPlayerScores)
    }

    fun getWinner() : Winner {
        if (playerScores.count { it.getWinner() == Winner.Player1 } >= 6) {
            return Winner.Player1
        }
        if (playerScores.count { it.getWinner() == Winner.Player2 } >= 6)  {
            return Winner.Player2
        }
        return Winner.None
    }

    companion object {
        fun startNewMatch(firstPlayerName: String, secondPlayerName: String) : Match = Match(
            0,
            firstPlayerName,
            secondPlayerName,
            listOf(Game.newGame())
        )
    }
}
