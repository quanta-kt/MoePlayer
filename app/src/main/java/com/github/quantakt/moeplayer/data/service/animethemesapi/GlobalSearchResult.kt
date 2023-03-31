package com.github.quantakt.moeplayer.data.service.animethemesapi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GlobalSearchResult(
    val search: Search
) {
    @Serializable
    data class Search(
        val anime: List<Anime>? = null,
        @SerialName("animethemes")
        val animeThemes: List<AnimeTheme>? = null,
    )
}