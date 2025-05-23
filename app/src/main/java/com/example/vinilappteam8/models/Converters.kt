package com.example.vinilappteam8.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromCollectorAlbumList(value: List<CollectorAlbum>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCollectorAlbumList(value: String): List<CollectorAlbum>? {
        val listType = object : TypeToken<List<CollectorAlbum>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromCommentList(value: List<Comment>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toCommentList(value: String): List<Comment>? {
        val listType = object : TypeToken<List<Comment>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromPerformerList(value: List<Performers>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toPerformerList(value: String): List<Performers>? {
        val listType = object : TypeToken<List<Performers>>() {}.type
        return gson.fromJson(value, listType)
    }
}