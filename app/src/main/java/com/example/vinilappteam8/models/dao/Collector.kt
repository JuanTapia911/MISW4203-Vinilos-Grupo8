package com.example.vinilappteam8.models.dao

import com.example.vinilappteam8.models.Comment
import com.example.vinilappteam8.models.Performer

data class CollectorAlbum(
    val id: Int,
    val price: Double?,
    val status: String?
)

data class Collector(
    val id: String,
    val name: String,
    val email: String,
    val telephone: String,
    val comments: List<Comment>,
    val favoritePerformers: List<Performer>,
    val collectorAlbums: List<CollectorAlbum>
)
