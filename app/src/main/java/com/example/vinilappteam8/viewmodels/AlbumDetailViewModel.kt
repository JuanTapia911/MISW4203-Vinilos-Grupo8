package com.example.vinilappteam8.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vinilappteam8.models.Album
import com.example.vinilappteam8.network.NetworkServiceAdapter

class AlbumDetailViewModel(application: Application) :  AndroidViewModel(application)  {

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> get() = _albums

    private val _albumDetail = MutableLiveData<Album?>()
    val albumDetail: LiveData<Album?> get() = _albumDetail

    private val _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean> get() = _eventNetworkError

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        // Aquí realizas una llamada para obtener todos los álbumes (puedes adaptar este método si es necesario)
        NetworkServiceAdapter.getInstance(getApplication()).getAlbums({
            _albums.postValue(it)
            _eventNetworkError.value = false
        }, {
            _eventNetworkError.value = true
        })
    }

    fun getAlbumById(albumId: Int) {
        // Llamada a tu método getAlbum para obtener el álbum por ID
        NetworkServiceAdapter.getInstance(getApplication()).getAlbum(albumId, { albumList ->
            // Aquí asumimos que el método devuelve una lista, pero solo tomamos el primer álbum
            val album = albumList.firstOrNull()
            _albumDetail.postValue(album)
        }, { error ->
            // Si ocurre un error de red, puedes manejarlo aquí
            _eventNetworkError.value = true
            Log.e("AlbumViewModel", "Error al obtener el álbum: ${error.message}")
        })
    }
}