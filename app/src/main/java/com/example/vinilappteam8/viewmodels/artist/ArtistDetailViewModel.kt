package com.example.vinilappteam8.viewmodels.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinilappteam8.models.CachedPerformer
import com.example.vinilappteam8.repository.PerformerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    private val performerRepository: PerformerRepository
): ViewModel() {

    private val _currentPerformer = MutableStateFlow<CachedPerformer?>(null)
    val currentPerformer = _currentPerformer

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    fun getPerformerById(id: Int) {
        viewModelScope.launch {

            _isLoading.value = true
            _errorMessage.value = null

            try {
                performerRepository.getArtist(id).collectLatest { performer ->
                    _currentPerformer.value = performer
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
                _isLoading.value = false
            }
        }
    }

}