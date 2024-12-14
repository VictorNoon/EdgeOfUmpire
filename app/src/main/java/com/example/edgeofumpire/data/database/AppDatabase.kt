package com.example.edgeofumpire.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.edgeofumpire.data.model.Match

@Database(entities = [Match::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun matchDao(): MatchDao

    companion object {
        @Volatile
        var instance: AppDatabase? = null

        fun getDataBaseFromContext(appContext: Context) : AppDatabase {
            instance?.let { return it }
            instance = Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "match"
            ).build()
            return instance!!
        }

        fun getDataBase() : AppDatabase = instance!!
    }
}