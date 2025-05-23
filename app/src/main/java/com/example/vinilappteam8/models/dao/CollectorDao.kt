package com.example.vinilappteam8.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.vinilappteam8.models.CachedCollector

@Dao
interface CollectorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(collector: List<CachedCollector>)

    @Transaction
    @Query("SELECT * FROM collector WHERE timestamp >= :timestamp")
    suspend fun getCachedCollectors(timestamp: Long): List<CachedCollector>

    @Query("SELECT * FROM collector WHERE id = :id")
    suspend fun getCollectorById(id: Int): CachedCollector?
}