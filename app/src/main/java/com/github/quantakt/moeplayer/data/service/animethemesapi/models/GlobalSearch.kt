package com.github.quantakt.moeplayer.data.service.animethemesapi.models

import com.github.quantakt.moeplayer.data.service.animethemesapi.models.resources.Anime
import com.github.quantakt.moeplayer.data.service.animethemesapi.models.resources.AnimeTheme
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


object GlobalSearch {

    @Serializable
    data class Result(
        val search: Search
    ) {
        @Serializable
        data class Search(
            val anime: List<Anime>? = null,
            @SerialName("animethemes")
            val animeThemes: List<AnimeTheme>? = null,
        )
    }

    enum class Fields(val apiName: String) {
        Anime("anime"),
        AnimeThemes("animethemes")
    }
}