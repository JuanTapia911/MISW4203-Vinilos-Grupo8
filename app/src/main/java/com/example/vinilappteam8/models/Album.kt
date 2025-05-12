package com.example.vinilappteam8.models

data class Album(
    val id: Int,
    val name: String?,
    val cover: String?,
    val releaseDate: String?,
    val description: String?,
    val genre: String?,
    val recordLabel: String?,
    val tracks: List<Track>?,
    val performers: List<Performer>?
)

data class AlbumList(
    val id: Int,
    val name: String?,
    val cover: String?,
    val genre: String?,
    val performer: String?
)