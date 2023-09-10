package com.github.quantakt.moeplayer.data.animethemes

import android.util.Log
import com.github.quantakt.moeplayer.api.AnimeThemesApi
import com.github.quantakt.moeplayer.api.models.resources.Anime
import com.github.quantakt.moeplayer.api.models.resources.AnimeTheme
import com.github.quantakt.moeplayer.api.models.resources.Search
import com.github.quantakt.moeplayer.api.models.resources.Video
import com.github.quantakt.moeplayer.model.SearchResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeThemeRepositoryImpl @Inject constructor(
    private val api: AnimeThemesApi
) : AnimeThemeRepository {

    override suspend fun globalSearch(query: String): SearchResult {
        return api.globalSearch.search(
            query,
            fields = listOf(
                Search.Fields.Anime,
                Search.Fields.AnimeThemes
            ),
            animeIncludes = listOf(
                Anime.Include.animethemes_animethemeentries_videos_audio,
                Anime.Include.images,
                Anime.Include.animethemes_song,
            ),
            animeThemeIncludes = listOf(
                AnimeTheme.Include.anime_images,
                AnimeTheme.Include.animethemeentries_videos,
                AnimeTheme.Include.song_artists,
            )
        ).toExternalModel()
    }

    /**
     * Returns a an audio URL corresponding to the given video basename
     */
    override suspend fun getAudioUrl(videoBasename: String): String {
        val video = api.videos.get(videoBasename, include = listOf(Video.Include.audio))

        return video.audio?.link // audio should never be null when the audio is specified as an include
            ?: video.link // a fallback just in case
            ?: throw NullPointerException() // Should never happen
    }
}