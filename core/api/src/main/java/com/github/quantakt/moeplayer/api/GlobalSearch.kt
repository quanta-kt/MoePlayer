package com.github.quantakt.moeplayer.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import kotlinx.serialization.Serializable
import com.github.quantakt.moeplayer.api.models.resources.Anime as AnimeResource
import com.github.quantakt.moeplayer.api.models.resources.AnimeTheme as AnimeThemeResource
import com.github.quantakt.moeplayer.api.models.resources.Search as SearchResource

class GlobalSearch internal constructor(
    private val httpClient: HttpClient
) {

    suspend fun search(
        query: String,
        fields: List<SearchResource.Fields> = emptyList(),
        animeIncludes: List<AnimeResource.Include> = emptyList(),
        animeThemeIncludes: List<AnimeThemeResource.Include> = emptyList(),
    ): SearchResource {

        @Serializable
        data class Result(val search: SearchResource)

        val result: Result = httpClient.get(AnimeThemesApi.BASE_URL) {

            url {
                appendPathSegments("search")
            }

            parameter("include[anime]", animeIncludes.joinToString(",") { it.apiName })

            parameter(
                "include[animetheme]",
                animeThemeIncludes.joinToString(",") { it.apiName }
            )

            parameter("fields[search]", fields.joinToString(",") { it.apiName })

            parameter("q", query)

        }.body()

        return result.search
    }
}