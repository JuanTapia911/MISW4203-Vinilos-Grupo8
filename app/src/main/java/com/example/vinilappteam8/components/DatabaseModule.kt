package com.example.vinilappteam8.components

import android.content.Context
import android.util.Log
import androidx.room.Room

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

import com.example.vinilappteam8.models.dao.*
import kotlin.jvm.java

/**
 * Este objecto es el "Modulo" de Hilt que proporciona las dependencias necesarias para la base de datos.
 * Hilt es un framework de inyección de dependencias para Android que simplifica la creación y gestión de dependencias.
 */

@Module //Anotacion de Hilt
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    /**
     * Proporciona una instancia de la base de datos de la aplicación.
     * @param context El contexto de la aplicación.
     * @return La instancia de la base de datos.
     */
    @Provides
    @Singleton
    fun provideAppDatabase( @ApplicationContext context: Context): AppDatabase {

        Log.d("DatabaseModule", "provideAppDatabase called")

        return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"

            ).fallbackToDestructiveMigration(false).build()

    }

    /**
     * Proporciona una instancia del DAO de Album.
     * @param database La instancia de la base de datos.
     * @return La instancia del DAO de Album.
     */
    @Provides
    fun provideAlbumDao(database: AppDatabase): AlbumDao {
        return database.AlbumDao()
    }

    /**
     * Proporciona una instancia del DAO de Canción.
     * @param database La instancia de la base de datos.
     * @return La instancia del DAO de Canción.
     */
    @Provides
    fun provideArtistDao(database: AppDatabase): PerformerDao {
        return database.PerformerDao()
    }

    @Provides
    fun provideCollectorDao(database: AppDatabase): CollectorDao {
        return database.CollectorDao()
    }
}