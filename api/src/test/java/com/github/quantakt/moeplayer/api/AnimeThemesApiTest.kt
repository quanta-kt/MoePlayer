package com.github.quantakt.moeplayer.api

import com.github.quantakt.moeplayer.api.models.GlobalSearch
import com.github.quantakt.moeplayer.api.models.resources.Anime
import com.github.quantakt.moeplayer.api.models.resources.AnimeTheme
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

    /**
     * Test that we can parse the JSON objects returned by the API with our models
     */
    @Test
    fun searchApiCompatibleWithModels(): Unit = runBlocking {
        val api = AnimeThemesApi(httpClient)

        val globalSearch = api.globalSearch(
            query = "a",
            fields = listOf(
                GlobalSearch.Fields.Anime,
                GlobalSearch.Fields.AnimeThemes
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
}