package com.github.quantakt.moeplayer.data.animethemes

import com.github.quantakt.moeplayer.model.SearchResult

interface AnimeThemeRepository {

    suspend fun globalSearch(query: String): SearchResult
    suspend fun getAudioUrl(videoBasename: String): String
}