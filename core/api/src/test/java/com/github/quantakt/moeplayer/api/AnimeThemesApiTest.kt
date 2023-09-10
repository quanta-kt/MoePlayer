package com.github.quantakt.moeplayer.api

import com.github.quantakt.moeplayer.api.models.resources.Anime
import com.github.quantakt.moeplayer.api.models.resources.AnimeTheme
import com.github.quantakt.moeplayer.api.models.resources.Search
import com.github.quantakt.moeplayer.api.models.resources.Video
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Test

class AnimeThemesApiTest {

    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                coerceInputValues = true
                ignoreUnknownKeys = true
            })
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = Logger.SIMPLE
        }
    }

    private val api = AnimeThemesApi(httpClient)

    /**
     * Test that we can parse the JSON objects returned by the API with our models
     */
    @Test
    fun searchApiCompatibleWithModels(): Unit = runBlocking {
        api.globalSearch.search(
            query = "yofukashi no uta",
            fields = listOf(
                Search.Fields.Anime,
                Search.Fields.AnimeThemes
            ), animeIncludes = listOf(
                Anime.Include.animethemes_animethemeentries_videos,
                Anime.Include.animethemes_animethemeentries_videos_audio,
                Anime.Include.animethemes_song,
                Anime.Include.images,
            ), animeThemeIncludes = listOf(
                AnimeTheme.Include.animethemeentries_videos,
                AnimeTheme.Include.song,
                AnimeTheme.Include.anime_images,
                AnimeTheme.Include.song_artists,
            )
        )
    }

    @Test
    fun videoApiCompatibleWithModels(): Unit = runBlocking {
        api.videos.get(
            "YofukashiNoUta-ED1.webm",
            include = listOf(Video.Include.audio)
        )
    }
}