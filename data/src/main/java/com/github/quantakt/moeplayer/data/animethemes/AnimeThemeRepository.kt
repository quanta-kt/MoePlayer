package com.github.quantakt.moeplayer.data.animethemes

import com.github.quantakt.moeplayer.domain.models.SearchResult

interface AnimeThemeRepository {

    suspend fun globalSearch(query: String): SearchResult
}