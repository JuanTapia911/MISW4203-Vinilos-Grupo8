package com.example.vinilappteam8.models

data class Performer (
    val id: Int,
    val name: String?,
    val image: String?,
    val description: String?,
    val birthDate: String?,
    val creationDate: String?,
    var type: String? = "Musician"
)