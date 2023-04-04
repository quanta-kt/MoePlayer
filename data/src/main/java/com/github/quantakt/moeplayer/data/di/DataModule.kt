package com.github.quantakt.moeplayer.data.di

import com.github.quantakt.moeplayer.data.animethemes.AnimeThemeRepositoryImpl
import com.github.quantakt.moeplayer.data.animethemes.AnimeThemeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindAnimeThemeRepository(
        animeThemeRepositoryImpl: AnimeThemeRepositoryImpl
    ): AnimeThemeRepository
}