package com.github.quantakt.moeplayer.api.models.resources

import com.github.quantakt.moeplayer.api.animethemesapi.models.resources.Song
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeTheme(
    val id: Long,
    val type: String? = null,
    val sequence: Int? = null,
    val group: String? = null,
    val slug: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("deleted_at")
    val deletedAt: String? = null,

    // relations
    val anime: Anime? = null,
    val song: Song? = null,
    @SerialName("animethemeentries")
    val animeThemeEntries: List<AnimeThemeEntry>? = null
) {
    enum class Include(
        val apiName: String
    ) {
        anime_images("anime.images"),
        animethemeentries_videos("animethemeentries.videos"),
        song("song"),
        song_artists("song.artists")
    }
}