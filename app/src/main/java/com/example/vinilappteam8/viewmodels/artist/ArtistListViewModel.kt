package com.example.vinilappteam8.viewmodels.artist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinilappteam8.models.Performer
import com.example.vinilappteam8.repository.PerformerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistListViewModel @Inject constructor(
    private val performerRepository: PerformerRepository
): ViewModel() {

    private val TAG = "ArtistViewModel"

    private val _performer = MutableStateFlow<List<Performer>>(emptyList())
    val performer = _performer.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        fetchArtists()
    }

    fun fetchArtists() {
        if(_performer.value.isNotEmpty()) return

        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val fetchedArtists = performerRepository.getArtists().first()
                _performer.value = fetchedArtists

            } catch (e: Exception) {
                Log.e(TAG, "Error fetching Artists (Musicians): ${e.message}")
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

}