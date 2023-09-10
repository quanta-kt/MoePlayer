package com.github.quantakt.moeplayer.api

import io.ktor.client.HttpClient
import javax.inject.Inject

class AnimeThemesApi @Inject constructor(
    private val httpClient: HttpClient
) {

    companion object {
        val BASE_URL = "https://api.animethemes.moe"
    }

    val globalSearch = GlobalSearch(httpClient)
    val videos = Videos(httpClient)
}