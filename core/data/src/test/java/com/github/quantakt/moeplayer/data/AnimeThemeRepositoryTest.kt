package com.github.quantakt.moeplayer.data

import com.github.quantakt.moeplayer.api.AnimeThemesApi
import com.github.quantakt.moeplayer.data.animethemes.AnimeThemeRepositoryImpl
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Test

class AnimeThemeRepositoryTest {

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

    @Test
    fun canSearchAnimeThemes() = runBlocking {
        val repo = AnimeThemeRepositoryImpl(AnimeThemesApi(httpClient))

        val response = repo.globalSearch("hamidashimono")

        assert(response.animeThemes.isNotEmpty())
    }
}