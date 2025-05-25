package com.example.vinilappteam8.repository

import com.example.vinilappteam8.components.LocalDataSource
import com.example.vinilappteam8.components.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CollectorRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {


    fun getFullCollectorInfo(collectorId: String) = flow {
        val cachedCollector = localDataSource.getCachedCollectors()
        if (cachedCollector.isNotEmpty()) {
            emit(cachedCollector)
        } else {
            val remoteCollector = remoteDataSource.getFullCollectorInfo(collectorId)
            localDataSource.insertCollector(remoteCollector.toCached())
            emit(remoteCollector.toCached())
        }
    }.flowOn(Dispatchers.IO)
}