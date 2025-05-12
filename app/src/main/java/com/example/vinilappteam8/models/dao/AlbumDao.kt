package com.example.vinilappteam8.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.vinilappteam8.models.CachedAlbum
import com.example.vinilappteam8.models.CachedAlbumPerformersCrossRef
import com.example.vinilappteam8.models.CachedAlbumWithPerformers

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(albums: List<CachedAlbum>)

    @Query("SELECT * FROM albums ORDER BY timestamp DESC")
    suspend fun getAllAlbums(): List<CachedAlbum>

    @Query("SELECT * FROM albums WHERE timestamp >= :timestamp")
    suspend fun getCachedAlbums(timestamp: Long): List<CachedAlbum>

    @Query("SELECT * FROM albums WHERE id = :id")
    suspend fun getAlbumById(id: Int): CachedAlbumWithPerformers?

    @Query("DELETE FROM albums")
    suspend fun clearAllAlbums()

    @Transaction
    @Query("SELECT * FROM albums where timestamp >= :timestamp")
    suspend fun getCachedAlbumsWithPerformers(timestamp: Long): List<CachedAlbumWithPerformers>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCachedAlbumCrossRef(crossRef: CachedAlbumPerformersCrossRef)

}