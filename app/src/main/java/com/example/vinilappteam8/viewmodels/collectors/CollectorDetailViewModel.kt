package com.example.vinilappteam8.viewmodels.collectors

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
class CollectorDetailViewModel @Inject constructor(
    private val collectorRepository: CollectorRepository
): ViewModel() {

    private val _currentCollector = MutableStateFlow<CachedCollector?>(null)
    val currentCollector: StateFlow<CachedCollector?> = _currentCollector.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()


    fun getCollectorById(id: Int) {
        viewModelScope.launch {

            _isLoading.value = true
            _errorMessage.value = null

            try {

                collectorRepository.getCollector(id).collectLatest { collector ->
                    _currentCollector.value = collector
                }

            } catch (e: Exception) {
                _errorMessage.value = e.message
                _isLoading.value = false
            }
        }
    }
}