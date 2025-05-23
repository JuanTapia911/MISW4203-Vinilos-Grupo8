package com.example.vinilappteam8.components

import android.util.Log
import com.example.vinilappteam8.models.dao.AlbumDao
import com.example.vinilappteam8.models.dao.PerformerDao
import com.example.vinilappteam8.models.CachedAlbum
import com.example.vinilappteam8.models.CachedAlbumPerformersCrossRef
import com.example.vinilappteam8.models.CachedAlbumWithPerformers
import com.example.vinilappteam8.models.CachedCollector
import com.example.vinilappteam8.models.CachedPerformer
import com.example.vinilappteam8.models.dao.CollectorDao
import javax.inject.Inject
import kotlin.collections.forEach

/**
 * LocalDataSource es la clase que se encarga de interactuar con la base de datos local.
 * Esta clase es responsable de insertar, obtener y limpiar los datos de la base de datos local.
 * Se utiliza para almacenar en caché los datos que se obtienen de la API.
 * Esta clase se "injecta" en el Repositorio para que pueda ser utilizada en la capa de presentación.
 * Es instanciada por Dagger Hilt, por eso se agrega la anotacion @Inject en el constructor.
 */

class LocalDataSource @Inject constructor(
    private val albumDao: AlbumDao,
    private val performerDao: PerformerDao,
    private val collectorDao: CollectorDao
) {

    private val TAG = "LocalDataSource" //para el Log

    init {
        Log.d(TAG, "LocalDataSource initialized")
    }

    // Operacion de Album en la base de datos local
    suspend fun insertCachedAlbums(albums: List<CachedAlbum>) {

        Log.d(TAG, "Inserting albums into local database")
        albumDao.insertAll(albums)

    }
    suspend fun getCachedAlbums(timestamp: Long): List<CachedAlbum> {

        Log.d(TAG, "Fetching cached albums from local database with timestamp $timestamp")
        return albumDao.getCachedAlbums(timestamp)

    }

    suspend fun getCachedAlbumById(id: Int): CachedAlbumWithPerformers? {

        Log.d(TAG, "Fetching cached album with id: $id from local database")
        return albumDao.getAlbumById(id)
    }

    suspend fun clearCachedAlbums() {
        Log.d(TAG, "Clearing cached albums from local database")
        albumDao.clearAllAlbums()
    }

    suspend fun getCachedAlbumsWithPerformers(timestamp: Long): List<CachedAlbumWithPerformers> {
        Log.d(TAG, "Fetching cached albums with performers from local database")
        return albumDao.getCachedAlbumsWithPerformers(timestamp)
    }

    suspend fun insertAlbumWithPerformers(albumWithPerformers: List<CachedAlbumWithPerformers>) {
        Log.d(TAG, "Inserting album with performers into local database")

        albumWithPerformers.forEach { albumWithPerformer ->
            albumDao.insertAll(listOf(albumWithPerformer.album))
            performerDao.insertAll(albumWithPerformer.performers)
        }
    }

    suspend fun getCachedCollectors(timestamp: Long): List<CachedCollector> {
        Log.d(TAG, "Fetching cached collectors with performers from local database")
        return collectorDao.getCachedCollectors(timestamp)
    }

    suspend fun insertCollector(collector: List<CachedCollector>) {
        Log.d(TAG, "Inserting collector  into local database")

        collector.forEach { refactor ->
            collectorDao.insertAll(listOf(refactor))
        }
    }

    suspend fun getCachedCollectorById(id: Int): CachedCollector? {

        Log.d(TAG, "Fetching cached collector with id: $id from local database")
        return collectorDao.getCollectorById(id)
    }

    /**
     * Esta funcion se encarga de insertar una referencia cruzada entre un album y sus artistas en la base de datos local.
     */
    suspend fun insertCachedAlbumCrossRef(crossRef: CachedAlbumPerformersCrossRef) {
        Log.d(TAG, "Inserting cached album cross reference into local database: $crossRef")
        albumDao.insertCachedAlbumCrossRef(crossRef)
    }


    // Operaciones de "Performer" en la base de datos local
    suspend fun insertPerformer(performers: List<CachedPerformer>) = performerDao.insertAll(performers)
    suspend fun getCachedPerformers(): List<CachedPerformer> = performerDao.getAllPerformers()
    suspend fun getCachedPerformerById(id: Int): CachedPerformer? = performerDao.getPerformerById(id)
    suspend fun clearCachedPerformers() = performerDao.clearAllPerformers()
}