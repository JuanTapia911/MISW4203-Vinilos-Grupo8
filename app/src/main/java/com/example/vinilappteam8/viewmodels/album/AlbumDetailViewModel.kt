package com.example.vinilappteam8.viewmodels.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class AlbumDetailViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
): ViewModel() {

    private val _currentAlbum = MutableStateFlow<CachedAlbumWithPerformers?>(null)
    val currentAlbum: StateFlow<CachedAlbumWithPerformers?> = _currentAlbum.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()


    fun getAlbumById(id: Int) {
        viewModelScope.launch {

            _isLoading.value = true
            _errorMessage.value = null

            try {

                albumRepository.getAlbum(id).collectLatest { album ->
                    _currentAlbum.value = album
                }

            } catch (e: Exception) {
                _errorMessage.value = e.message
                _isLoading.value = false
            }
        }
    }


}