package com.example.edgeofumpire.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.edgeofumpire.data.model.MATCH_TABLE
import com.example.edgeofumpire.data.model.Match
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(match: Match) : Long

    @Query("SELECT * FROM $MATCH_TABLE")
    fun getAll(): Flow<List<Match>>

    @Query("SELECT * FROM $MATCH_TABLE WHERE uid IN (:id)")
    fun getById(id: Long): Flow<Match>?

    @Query("DELETE FROM $MATCH_TABLE WHERE uid = :uid")
    fun deleteById(uid: Long)
}