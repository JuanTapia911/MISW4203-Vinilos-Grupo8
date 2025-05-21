package com.example.vinilappteam8.repository

import android.util.Log
import com.example.vinilappteam8.components.LocalDataSource
import com.example.vinilappteam8.components.RemoteDataSource
import com.example.vinilappteam8.models.CachedCollector
import com.example.vinilappteam8.models.Collector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CollectorRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    private val TAG = "CollectorRepository"
    private val TIMESTAMP_THRESHOLD = 72 * 60 * 60 * 1000 // 72 hours in milliseconds

    init {
        Log.d(TAG, "CollectorRepository initialized")
    }

    fun getCollectors(): Flow<List<CachedCollector>> = flow {

        val timestamp = System.currentTimeMillis() -TIMESTAMP_THRESHOLD
        val cachedResults = localDataSource.getCachedCollectors(timestamp)

        if(cachedResults.isNotEmpty()) {

            Log.d(TAG, "Cached results found: $cachedResults")
            emit(cachedResults)

        } else {
            Log.d(TAG, "No cached results found")
        }

        try {

            Log.d(TAG, "Fetching remote results")
            val remoteResults = remoteDataSource.getCollectors()

            Log.d(TAG, "Transforming remote results to cached data")
            val cachedData: List<CachedCollector> = remoteResults.map {
                CachedCollector(
                    id = it.id,
                    name = it.name,
                    email = it.email,
                    telephone = it.telephone,
                )
            }

            localDataSource.insertCollector(cachedData)

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

    /*fun getAlbum(id: Int): Flow<CachedCollectorWithPerformers?> = flow {

        val cachedAlbum = localDataSource.getCachedAlbumById(id)
        if (cachedAlbum != null) {
            Log.d(TAG, "Cached album found: $cachedAlbum")
            emit(cachedAlbum)
        } else {
            Log.d(TAG, "No cached album found")
        }

    }.flowOn(Dispatchers.IO)


     */
    private fun CachedCollector.toDomain(): Collector = Collector(
        id,
        name.toString(),
        email.toString(),
        telephone.toString(),
    )

    private fun Collector.toCached(): CachedCollector = CachedCollector(
        id, name, email, telephone
    )
}