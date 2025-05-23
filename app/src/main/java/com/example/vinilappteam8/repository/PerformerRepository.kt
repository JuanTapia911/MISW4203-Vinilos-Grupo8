package com.example.vinilappteam8.repository

import com.example.vinilappteam8.components.LocalDataSource
import com.example.vinilappteam8.components.RemoteDataSource
import com.example.vinilappteam8.models.Performer
import com.example.vinilappteam8.models.CachedPerformer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PerformerRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {
    fun getArtists(): Flow<List<Performer>> = flow {

        val cachedArtists = localDataSource.getCachedPerformers().map { it.toDomain() }
        emit(cachedArtists)

        try {

            val remoteArtists = remoteDataSource.getArtists()
            localDataSource.clearCachedPerformers()
            localDataSource.insertPerformer(remoteArtists.map { it.toCached() })
            emit(remoteArtists)

        } catch (e: Exception) {
            if (cachedArtists.isEmpty()) {
                throw e
            }
        }

    }.flowOn(Dispatchers.IO)

    fun getArtist(id: Int): Flow<Performer> = flow {

        val cachedArtist = localDataSource.getCachedPerformerById(id)?.toDomain()
        cachedArtist?.let { emit(it) }

        try {

            val remoteArtist = remoteDataSource.getArtist(id)
            localDataSource.insertPerformer(listOf(remoteArtist.toCached()))
            emit(remoteArtist)

        } catch (e: Exception) {
            if (cachedArtist == null) {
                throw e
            }
        }
    }

    private fun CachedPerformer.toDomain(): Performer = Performer(
        id,
        name.toString(),
        image.toString(),
        description.toString(),
        birthDate.toString(),
        creationDate.toString(),
        type.toString(),
        emptyList()
    )
    private fun Performer.toCached(): CachedPerformer = CachedPerformer(
        id, name, image, description, birthDate, creationDate, type, System.currentTimeMillis()
    )
}