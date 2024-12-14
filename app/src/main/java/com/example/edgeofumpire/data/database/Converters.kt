package com.example.edgeofumpire.data.database

import androidx.room.TypeConverter
import com.example.edgeofumpire.data.model.Game
import com.example.edgeofumpire.data.model.Match
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun fromMatchToString(value: Match) : String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromStringToMatch(value: String) : Match {
        return Json.decodeFromString(value)
    }

    @TypeConverter
    fun fromListOfGameToString(value: List<Game>) : String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun fromStringToListOfGame(value: String) : List<Game> {
        return Json.decodeFromString(value)
    }
}