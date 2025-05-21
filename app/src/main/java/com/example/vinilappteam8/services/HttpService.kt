package com.example.vinilappteam8.services

import com.example.vinilappteam8.models.Album
import com.example.vinilappteam8.models.Performer
import retrofit2.http.GET
import retrofit2.http.Path

interface HttpService {

    @GET("/albums")
    suspend fun getAlbums(): List<Album>

    @GET("/albums/{id}")
    suspend fun getAlbumById(@Path("id") id: Int): Album

    @GET("/musicians")
    suspend fun getArtists(): List<Performer>

    @GET("/musicians/{id}")
    suspend fun getArtistById(@Path("id") id: Int): Performer

    @GET("/bands")
    suspend fun getBands(): List<Performer>

    @GET("/bands/{id}")
    suspend fun getBandById(@Path("id") id: Int): Performer
}