package com.example.vinilappteam8.viewmodels.album

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinilappteam8.models.Album
import com.example.vinilappteam8.models.CachedAlbumWithPerformers
import com.example.vinilappteam8.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
): ViewModel() {

    private val _albums = MutableStateFlow<List<CachedAlbumWithPerformers>>(emptyList())
    val albums: StateFlow<List<CachedAlbumWithPerformers>> = _albums.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {

        fetchAlbumsWithPerformers()
    }


    private fun fetchAlbumsWithPerformers() {
        viewModelScope.launch {

            _isLoading.value = true
            _errorMessage.value = null

            try {

                albumRepository.getAlbumWithPerformers().collectLatest { list ->
                    _albums.value = list
                }

            } catch (e: Exception) {

                Log.e("AlbumListViewModel", "Error fetching albums with performers: ${e.message}")
                _errorMessage.value = e.message

            } finally {
                _isLoading.value = false
            }
        }

    }


}