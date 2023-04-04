package com.github.quantakt.moeplayer.domain.models

data class SearchResult(
    val anime: List<Anime>,
    val animeThemes: List<AnimeTheme>
) {
    data class Anime(
        val id: Long,
        val slug: String,
        val title: String,
        val imageUrl: String?,
        val synopsis: String,
    )

    data class AnimeTheme(
        val id: Long,
        val slug: String,
        val title: String,
        val animeTitle: String,
        val imageUrl: String?,
        val audioUrl: String,
    )
}