package com.github.quantakt.moeplayer.model

data class SearchResult(
    val anime: List<Anime>,
    val animeThemes: List<AnimeTheme>
) {
    data class Anime(
        val id: Long,
        val title: String,
        val imageUrl: String?,
        val synopsis: String,
        val season: String,
        val year: Int,
    )

    data class AnimeTheme(
        val id: Long,
        val title: String,
        val animeTitle: String,
        val imageUrl: String?,
        val type: String?,
        val sequence: Int?,
        val video: Video,
    )

    data class Video(
        val basename: String,
        val url: String,
    )
}