package com.example.vinilappteam8.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


data class MyNavManagerState(

    val isInitialScreenVisible: Boolean = true,
    val selectedNavItem: String = "Albums",
    val currentTitle: String = "VinilApp Team 8 - $selectedNavItem"

)

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

    private val _currentState = MutableStateFlow(MyNavManagerState())
    val currentState: StateFlow<MyNavManagerState> = _currentState.asStateFlow()

    fun updateTitle(newTitle: String) {
        _currentState.update { currentState ->
            currentState.copy(currentTitle = newTitle)
        }
    }

    fun updateSelectedNavItem(newItem: String) {
        _currentState.update { currentState ->
            currentState.copy(selectedNavItem = newItem)
        }
    }

    fun updateInitialScreen(newValue: Boolean) {
        _currentState.update { currentState ->
            currentState.copy(isInitialScreenVisible = newValue)
        }
    }
}