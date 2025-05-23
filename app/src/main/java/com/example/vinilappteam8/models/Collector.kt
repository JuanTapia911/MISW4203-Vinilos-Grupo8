package com.example.vinilappteam8.models

data class Collector(
    val id: Int,
    val name: String,
    val email: String,
    val telephone: String,
    val collectorAlbums: List<CollectorAlbum>,
    val comments: List<Comment>,
    val favoritePerformers: List<Performers>
)