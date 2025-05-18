package com.example.vinilappteam8.components

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.vinilappteam8.models.*
import com.example.vinilappteam8.models.dao.*


/**
 * AppDatabase Es la clase principal para usar la base de datos local
 * Extiende de RoomDatabase y se agregan las funciones relacionadas a cada DAO en la aplicacion
 */
@Database(
    entities = [CachedAlbum::class, CachedPerformer::class, CachedAlbumPerformersCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun AlbumDao(): AlbumDao
    abstract fun PerformerDao(): PerformerDao
}