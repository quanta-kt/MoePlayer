package com.github.quantakt.moeplayer.data.animethemes

import com.github.quantakt.moeplayer.api.models.resources.Search
import com.github.quantakt.moeplayer.model.SearchResult

internal fun Search.toExternalModel(): SearchResult {
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
        animeThemes = animeThemes.orEmpty().mapNotNull { animeTheme ->

            val video = animeTheme.animeThemeEntries?.firstOrNull()?.videos?.firstOrNull()
                ?: return@mapNotNull null

            SearchResult.AnimeTheme(
                id = animeTheme.id,
                title = animeTheme.song?.title ?: "",
                animeTitle = animeTheme.anime?.name ?: "",
                imageUrl = animeTheme.anime?.images?.firstOrNull()?.link,
                type = animeTheme.type,
                sequence = animeTheme.sequence,
                video = SearchResult.Video(
                    basename = video.basename,
                    url = video.link
                        // FIXME: Should we make the source field non-null?
                        ?: return@mapNotNull null
                ),
            )
        }
    )
}