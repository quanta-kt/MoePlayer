package com.github.quantakt.moeplayer.di

import com.github.quantakt.moeplayer.data.repository.animethemes.AnimeThemeRepositoryImpl
import com.github.quantakt.moeplayer.domain.repository.AnimeThemeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.logging.Logger
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.logging.*
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindAnimeThemeRepository(
        animeThemeRepositoryImpl: AnimeThemeRepositoryImpl
    ): AnimeThemeRepository

    companion object {
        @Provides
        fun provideKtorClient(): HttpClient {
            return HttpClient(CIO) {
                install(ContentNegotiation) {
                    json(Json {
                        coerceInputValues = true
                        ignoreUnknownKeys = true
                    })
                }

                install(Logging) {
                    level = LogLevel.ALL
                    logger = Logger.ANDROID
                }
            }
        }
    }
}