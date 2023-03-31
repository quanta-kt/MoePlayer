package com.github.quantakt.moeplayer.data.service.animethemesapi

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject


class AnimeThemesApi @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun globalSearch(
        query: String,
        animeIncludes: List<Anime.Include> = emptyList(),
        animeThemeIncludes: List<AnimeTheme.Include> = emptyList(),
    ): GlobalSearchResult {
        return httpClient.get("https://api.animethemes.moe/search") {
            parameter("include[anime]", animeIncludes.joinToString(",") { it.apiName })
            parameter("include[animetheme]", animeThemeIncludes.joinToString(",") { it.apiName })
            parameter("q", query)
        }.body()
    }
}