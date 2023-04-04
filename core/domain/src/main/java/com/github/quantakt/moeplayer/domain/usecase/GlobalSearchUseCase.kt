package com.github.quantakt.moeplayer.domain.usecase

import com.github.quantakt.moeplayer.data.animethemes.AnimeThemeRepository
import com.github.quantakt.moeplayer.model.SearchResult
import javax.inject.Inject

class GlobalSearchUseCase @Inject constructor(
    private val repository: AnimeThemeRepository
) {

    suspend operator fun invoke(query: String): SearchResult {
        return repository.globalSearch(query)
    }
}