package com.github.quantakt.moeplayer.player.di

import android.app.Application
import com.github.quantakt.moeplayer.player.MediaPlayer
import com.github.quantakt.moeplayer.player.MediaPlayerImpl
import com.google.android.exoplayer2.ExoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object PlayerModule {

    @Provides
    fun provideExoplayer(application: Application): ExoPlayer {
        return ExoPlayer.Builder(application)
            .build()
    }

    @Provides
    fun providePlayer(exoPlayer: ExoPlayer): MediaPlayer {
        return MediaPlayerImpl(exoPlayer)
    }
}