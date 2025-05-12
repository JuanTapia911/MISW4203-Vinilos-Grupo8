package com.example.vinilappteam8.repository

import android.util.Log
import com.example.vinilappteam8.components.LocalDataSource
import com.example.vinilappteam8.components.RemoteDataSource
import com.example.vinilappteam8.models.Album
import com.example.vinilappteam8.models.CachedAlbum
import com.example.vinilappteam8.models.CachedAlbumPerformersCrossRef
import com.example.vinilappteam8.models.CachedAlbumWithPerformers
import com.example.vinilappteam8.models.CachedPerformer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AlbumRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    private val TAG = "AlbumRepository"
    private val TIMESTAMP_THRESHOLD = 72 * 60 * 60 * 1000 // 72 hours in milliseconds

    init {
        Log.d(TAG, "AlbumRepository initialized")
    }

    fun getAlbumWithPerformers(): Flow<List<CachedAlbumWithPerformers>> = flow {

        val timestamp = System.currentTimeMillis() -TIMESTAMP_THRESHOLD
        val cachedResults = localDataSource.getCachedAlbumsWithPerformers(timestamp)

        if(cachedResults.isNotEmpty()) {

            Log.d(TAG, "Cached results found: $cachedResults")
            emit(cachedResults)

        } else {
            Log.d(TAG, "No cached results found")
        }

        try {

            Log.d(TAG, "Fetching remote results")
            val remoteResults = remoteDataSource.getAlbums()

            Log.d(TAG, "Transforming remote results to cached data")
            val cachedData: List<CachedAlbumWithPerformers> = remoteResults.map {
                CachedAlbumWithPerformers(
                    album = it.toCached(),
                    performers = it.performers?.map {
                        CachedPerformer(
                            id = it.id,
                            name = it.name,
                            image = it.image,
                            description = it.description,
                            birthDate = it.birthDate,
                            creationDate = it.creationDate,
                            type = it.type
                        )
                    } ?: emptyList()
                )
            }

            localDataSource.insertAlbumWithPerformers(cachedData)
            cachedData.forEach {
                it.performers.forEach { performer ->
                    localDataSource.insertCachedAlbumCrossRef(CachedAlbumPerformersCrossRef(it.album.id, performer.id))
                }
            }
            emit(cachedData)

        } catch (e: Exception) {
            Log.e(TAG, "Error fetching remote results: ${e.message}")
            if (cachedResults.isEmpty()) {
                throw e
            }
        }

        Log.d(TAG, "Cached results: $cachedResults")

        emit(value = cachedResults)

    }.flowOn(Dispatchers.IO)

    fun getAlbum(id: Int): Flow<CachedAlbumWithPerformers?> = flow {

        val cachedAlbum = localDataSource.getCachedAlbumById(id)
        if (cachedAlbum != null) {
            Log.d(TAG, "Cached album found: $cachedAlbum")
            emit(cachedAlbum)
        } else {
            Log.d(TAG, "No cached album found")
        }

    }.flowOn(Dispatchers.IO)

    private fun CachedAlbum.toDomain(): Album = Album(
        id,
        name.toString(),
        cover.toString(),
        releaseDate.toString(), description.toString(),
        genre.toString(), recordLabel.toString(), emptyList(), emptyList()
    )

    private fun Album.toCached(): CachedAlbum = CachedAlbum(
        id, name, cover, releaseDate, description, genre, recordLabel
    )

}


