package com.github.quantakt.moeplayer.domain.usecase

import com.github.quantakt.moeplayer.data.animethemes.AnimeThemeRepository
import javax.inject.Inject

class GetAudioUrlUseCase @Inject constructor(private val repository: AnimeThemeRepository) {

    suspend operator fun invoke(videoBasename: String): String {
        return repository.getAudioUrl(videoBasename)
    }
}