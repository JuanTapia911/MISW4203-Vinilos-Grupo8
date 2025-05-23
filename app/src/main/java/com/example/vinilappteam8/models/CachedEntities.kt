package com.example.vinilappteam8.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "albums")
data class CachedAlbum(

    @PrimaryKey val id: Int,
    val name: String?,
    val cover: String?,
    val releaseDate: String?,
    val description: String?,
    val genre: String?,
    val recordLabel: String?,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "performers")
data class CachedPerformer(
    @PrimaryKey val id: Int,
    val name: String?,
    val image: String?,
    val description: String?,
    val birthDate: String?,
    val creationDate: String?,
    val type: String?,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(primaryKeys = ["albumId", "performerId"],
    foreignKeys = [
        ForeignKey(
            entity = CachedAlbum::class,
            parentColumns = ["id"], // Assuming "albumId" is the primary key in your Album entity
            childColumns = ["albumId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CachedPerformer::class,
            parentColumns = ["id"], // Assuming "performerId" is the primary key in your Performer entity
            childColumns = ["performerId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class CachedAlbumPerformersCrossRef(
    val albumId: Int,
    val performerId: Int
)

data class CachedAlbumWithPerformers(
    @Embedded val album: CachedAlbum,
    @Relation(
        parentColumn = "id",
        entity = CachedPerformer::class,
        entityColumn = "id",
        associateBy = Junction(
            value = CachedAlbumPerformersCrossRef::class,
            parentColumn = "albumId",
            entityColumn = "performerId"
        )
    )
    val performers: List<CachedPerformer>
)

data class CachedPerformerWithAlbums(
    @Embedded val performer: CachedPerformer,
    @Relation(
        parentColumn = "id",
        entity = CachedAlbum::class,
        entityColumn = "id",
        associateBy = Junction(
            value = CachedAlbumPerformersCrossRef::class,
            parentColumn = "performerId",
            entityColumn = "albumId"

        )
    )
    val albums: List<CachedAlbum>
)



@Entity(tableName = "collector")
data class CachedCollector(
    @PrimaryKey val id: Int,
    val name: String?,
    val telephone: String?,
    val email: String?,
    val timestamp: Long = System.currentTimeMillis(),
    val collectorAlbums: List<CollectorAlbum>,
    val comments: List<Comment>,
    val favoritePerformers: List<Performers>
)

@Entity(tableName = "collector_album")
data class CachedCollectorAlbum(
    @PrimaryKey val id: Int,
    val price: Int,
    val status: String
)
@Entity(tableName = "comment")
data class CachedComment(
    @PrimaryKey val id: Int,
    val description: String,
    val rating: Int
)

@Entity(tableName = "performer")
data class CachedPerformers(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val birthDate: String, // Para simplificar guarda la fecha como String (ISO8601)
    val image: String?
)
// En un archivo como MapperExtensions.kt (fuera de CollectorRepository)


fun CollectorAlbum.toCached() = CachedCollectorAlbum(id, price, status)

fun Comment.toCached() = CachedComment(id, description, rating)


fun Performers.toCached() = CachedPerformers(id, name, description, birthDate, image)

fun CachedCollectorAlbum.toDomain() = CollectorAlbum(id, price, status)

fun CachedComment.toDomain() = Comment(id   , description, rating)

fun CachedPerformer.toDomain() = Performers(id, name.toString(),
    description.toString(), birthDate.toString(), image.toString()
)


