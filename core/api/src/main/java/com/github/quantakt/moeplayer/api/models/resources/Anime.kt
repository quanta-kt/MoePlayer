package com.github.quantakt.moeplayer.api.models.resources

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Anime(
    val id: Long,
    val name: String,
    val slug: String,
    val year: Int,
    val season: String,
    val synopsis: String? = null,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("deleted_at")
    val deletedAt: String? = null,

    // relations
    @SerialName("animethemes")
    val animeThemes: List<AnimeTheme>? = null,
    val images: List<Image>? = null,
) {
    enum class Include(
        val apiName: String
    ) {
        animethemes_animethemeentries_videos("animethemes.animethemeentries.videos"),
        animethemes_animethemeentries_videos_audio("animethemes.animethemeentries.videos.audio"),
        animethemes_song("animethemes.song"),

        images("images"),
    }
}