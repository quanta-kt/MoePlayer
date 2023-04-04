package com.github.quantakt.moeplayer.domain.repository

import com.github.quantakt.moeplayer.data.repository.animethemes.SearchResult

interface AnimeThemeRepository {

    suspend fun globalSearch(query: String): SearchResult
}