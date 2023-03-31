package com.github.quantakt.moeplayer.data.service.animethemesapi

import com.github.quantakt.moeplayer.data.service.animethemesapi.models.GlobalSearch
import com.github.quantakt.moeplayer.data.service.animethemesapi.models.resources.Anime
import com.github.quantakt.moeplayer.data.service.animethemesapi.models.resources.AnimeTheme
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject


class AnimeThemesApi @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun globalSearch(
        query: String,
        fields: List<GlobalSearch.Fields> = emptyList(),
        animeIncludes: List<Anime.Include> = emptyList(),
        animeThemeIncludes: List<AnimeTheme.Include> = emptyList(),
    ): GlobalSearch.Result {

        return httpClient.get("https://api.animethemes.moe/search") {

            parameter("include[anime]", animeIncludes.joinToString(",") { it.apiName })

            parameter(
                "include[animetheme]",
                animeThemeIncludes.joinToString(",") { it.apiName }
            )

            parameter("fields[search]", fields.joinToString(",") { it.apiName })

            parameter("q", query)

        }.body()
    }
}