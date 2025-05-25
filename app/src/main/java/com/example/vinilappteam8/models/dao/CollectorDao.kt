package com.example.vinilappteam8.models.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.vinilappteam8.models.CachedCollectorWithComments

@Dao
interface CollectorDao {

    @Transaction
    @Query("SELECT * FROM collector")
    suspend fun getFullCollector(): List<CachedCollectorWithComments>
}