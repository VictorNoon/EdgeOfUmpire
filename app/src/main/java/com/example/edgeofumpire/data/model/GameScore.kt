package com.example.edgeofumpire.data.model

import kotlinx.serialization.Serializable

@Serializable
enum class GameScore {
    Zero,
    Fifteen,
    Thirty,
    Forty,
    Won
}

fun GameScore.toPrettyDisplay() : String {
    return when(this) {
        GameScore.Zero -> " 0"
        GameScore.Fifteen -> "15"
        GameScore.Thirty -> "30"
        GameScore.Forty -> "40"
        GameScore.Won -> " W"
    }
}

fun GameScore.increase() : GameScore {
    return when(this) {
        GameScore.Zero -> GameScore.Fifteen
        GameScore.Fifteen -> GameScore.Thirty
        GameScore.Thirty -> GameScore.Forty
        GameScore.Forty -> GameScore.Won
        GameScore.Won -> GameScore.Won
    }
}


fun GameScore.decrease() : GameScore {
    return when(this) {
        GameScore.Zero -> GameScore.Zero
        GameScore.Fifteen -> GameScore.Zero
        GameScore.Thirty -> GameScore.Fifteen
        GameScore.Forty -> GameScore.Thirty
        GameScore.Won -> GameScore.Forty
    }
}