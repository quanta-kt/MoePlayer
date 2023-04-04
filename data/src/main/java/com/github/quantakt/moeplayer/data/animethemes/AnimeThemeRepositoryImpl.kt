package com.github.quantakt.moeplayer.data.animethemes

import com.github.quantakt.moeplayer.api.AnimeThemesApi
import com.github.quantakt.moeplayer.api.models.GlobalSearch
import com.github.quantakt.moeplayer.api.models.resources.Anime
import com.github.quantakt.moeplayer.api.models.resources.AnimeTheme
import com.github.quantakt.moeplayer.domain.models.SearchResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeThemeRepositoryImpl @Inject constructor(
    private val api: AnimeThemesApi
) : AnimeThemeRepository {

    override suspend fun globalSearch(query: String): SearchResult {
        return api.globalSearch(
            query,
            fields = listOf(
                GlobalSearch.Fields.Anime,
                GlobalSearch.Fields.AnimeThemes
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
        ).search.toExternalModel()
    }
}