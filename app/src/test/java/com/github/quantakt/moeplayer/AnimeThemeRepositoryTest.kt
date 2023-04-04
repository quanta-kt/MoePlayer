package com.github.quantakt.moeplayer

import com.github.quantakt.moeplayer.data.repository.animethemes.AnimeThemeRepositoryImpl
import com.github.quantakt.moeplayer.data.service.animethemesapi.AnimeThemesApi
import com.github.quantakt.moeplayer.di.NetworkModule
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AnimeThemeRepositoryTest {

    @Test
    fun canSearchAnimeThemes() = runBlocking {
        val repo = AnimeThemeRepositoryImpl(
            AnimeThemesApi(
                NetworkModule.provideKtorClient()
            )
        )

        val response = repo.globalSearch("hamidashimono")

        assert(response.animeThemes.isNotEmpty())
    }
}