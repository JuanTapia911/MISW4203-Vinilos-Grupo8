package com.example.vinilappteam8.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vinilappteam8.models.CachedPerformer

@Dao
interface PerformerDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(artists: List<CachedPerformer>)

    @Query("SELECT * FROM performers ORDER BY timestamp DESC")
    suspend fun getAllPerformers(): List<CachedPerformer>

    @Query("SELECT * FROM performers WHERE id = :id")
    suspend fun getPerformerById(id: Int): CachedPerformer?

    @Query("DELETE FROM performers")
    suspend fun clearAllPerformers()
}