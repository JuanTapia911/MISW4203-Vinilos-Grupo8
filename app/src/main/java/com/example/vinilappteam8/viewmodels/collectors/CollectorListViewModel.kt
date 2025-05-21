package com.example.vinilappteam8.viewmodels.collectors

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vinilappteam8.models.CachedCollector
import com.example.vinilappteam8.repository.CollectorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectorListViewModel @Inject constructor(
    private val collectorRepository: CollectorRepository
):ViewModel()   {
    private val _collectors = MutableStateFlow<List<CachedCollector>>(emptyList())
    val collectors: StateFlow<List<CachedCollector>> = _collectors
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {

        fetchCollectors()
    }

    private fun fetchCollectors() {
        viewModelScope.launch {

            _isLoading.value = true
            _errorMessage.value = null

            try {

                collectorRepository.getCollectors().collectLatest { list ->
                    _collectors.value = list
                }

            } catch (e: Exception) {

                Log.e("CollectorsListViewModel", "Error fetching albums with performers: ${e.message}")
                _errorMessage.value = e.message

            } finally {
                _isLoading.value = false
            }
        }

    }
}