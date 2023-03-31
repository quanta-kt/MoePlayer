package com.github.quantakt.moeplayer

import com.github.quantakt.moeplayer.data.service.animethemesapi.Anime
import com.github.quantakt.moeplayer.data.service.animethemesapi.AnimeTheme
import com.github.quantakt.moeplayer.data.service.animethemesapi.AnimeThemesApi
import com.github.quantakt.moeplayer.di.NetworkModule
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AnimeThemesApiTest {

    /**
     * Test that we can parse the JSON objects returned by the API with our models
     */
    @Test
    fun searchApiCompatibleWithModels(): Unit = runBlocking {
        val api = AnimeThemesApi(NetworkModule.provideKtorClient())

        api.globalSearch(
            query = "a",
            animeIncludes = listOf(
                Anime.Include.animethemes_animethemeentries_videos,
                Anime.Include.animethemes_animethemeentries_videos_audio,
                Anime.Include.animethemes_song,
                Anime.Include.images,
            ),
            animeThemeIncludes = listOf(
                AnimeTheme.Include.animethemeentries_videos,
                AnimeTheme.Include.song,
                AnimeTheme.Include.anime_images,
                AnimeTheme.Include.song_artists,
            )
        )
    }
}