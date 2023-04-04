package com.github.quantakt.moeplayer.features.home

import com.github.quantakt.moeplayer.model.SearchResult

data class HomeScreenState(
    val query: String = "",
    val results: SearchResult? = null,
)