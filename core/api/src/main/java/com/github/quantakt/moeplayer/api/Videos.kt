package com.github.quantakt.moeplayer.api

import com.github.quantakt.moeplayer.api.models.resources.Video
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.appendPathSegments
import kotlinx.serialization.Serializable

class Videos(
    private val httpClient: HttpClient
) {

    suspend fun get(basename: String, include: List<Video.Include> = emptyList()): Video {

        @Serializable
        data class Result(val video: Video)

        val result: Result = httpClient.get(AnimeThemesApi.BASE_URL) {
            url {
                appendPathSegments("video")
                appendPathSegments(basename)
            }

            if (include.isNotEmpty()) {
                parameter("include", include.joinToString(",") { it.apiName })
            }
        }.body()

        return result.video
    }
}