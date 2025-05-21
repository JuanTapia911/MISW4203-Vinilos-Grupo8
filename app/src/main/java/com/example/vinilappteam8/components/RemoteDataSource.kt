package com.example.vinilappteam8.components

import com.example.vinilappteam8.models.Album
import com.example.vinilappteam8.models.Performer
import com.example.vinilappteam8.services.HttpService
import javax.inject.Inject

/**
 * Esta clase es responsable de la comunicación con el servicio remoto para obtener datos.
 * Se utiliza la inyección de dependencias para proporcionar una instancia de HttpService.
 * @param httpService Instancia de HttpService para realizar las peticiones HTTP.
 * */
class RemoteDataSource @Inject constructor(private val httpService: HttpService)  {

    /*
     * Métodos suspendidos para obtener datos de álbumes y artistas.
     * Estos métodos se pueden llamar desde una coroutine o desde otra función suspendida.
     * */

    suspend fun getAlbums(): List<Album> = httpService.getAlbums()
    suspend fun getAlbum(id: Int): Album = httpService.getAlbumById(id)

    suspend fun getBands(): List<Performer> = httpService.getBands()
    suspend fun getBand(id: Int): Performer = httpService.getBandById(id)

    suspend fun getArtists(): List<Performer> = httpService.getArtists()
    suspend fun getArtist(id: Int): Performer = httpService.getArtistById(id)

}