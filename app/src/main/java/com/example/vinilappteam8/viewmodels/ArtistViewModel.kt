package com.example.vinilappteam8.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArtistViewModel: ViewModel() {

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    fun setName(name: String) {
        _name.value = name
    }

}