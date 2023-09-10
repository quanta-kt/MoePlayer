package com.github.quantakt.moeplayer.api.models.resources

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Search(
    val anime: List<Anime>? = null,
    @SerialName("animethemes")
    val animeThemes: List<AnimeTheme>? = null,
) {
    enum class Fields(val apiName: String) {
        Anime("anime"),
        AnimeThemes("animethemes")
    }
}