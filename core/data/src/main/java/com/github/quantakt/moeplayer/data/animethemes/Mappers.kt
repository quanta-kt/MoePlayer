package com.github.quantakt.moeplayer.data.animethemes

import com.github.quantakt.moeplayer.api.models.GlobalSearch
import com.github.quantakt.moeplayer.model.SearchResult

private const val AUDIO_URL_FORMAT = "https://a.animethemes.moe/%s.ogg"

internal fun GlobalSearch.Result.Search.toExternalModel(): SearchResult {
    return SearchResult(
        anime = anime.orEmpty().map {
            SearchResult.Anime(
                id = it.id,
                title = it.name,
                imageUrl = it.images?.firstOrNull()?.link,
                synopsis = it.synopsis.orEmpty(),
                season = it.season,
                year = it.year,
            )
        },
        animeThemes = animeThemes.orEmpty()
            .asIterable()
            .map { animeTheme ->

                val filename = animeTheme.animeThemeEntries
                    ?.firstOrNull()
                    ?.videos
                    ?.firstOrNull()
                    ?.filename ?: return@map null

                SearchResult.AnimeTheme(
                    id = animeTheme.id,
                    title = animeTheme.song?.title ?: "",
                    animeTitle = animeTheme.anime?.name ?: "",
                    imageUrl = animeTheme.anime?.images?.firstOrNull()?.link,
                    audioUrl = AUDIO_URL_FORMAT.format(filename),
                    type = animeTheme.type,
                    sequence = animeTheme.sequence,
                )
            }.filterNotNull()
    )
}