package com.github.quantakt.moeplayer.player

import kotlinx.coroutines.flow.StateFlow

interface MediaPlayer {

    data class PlayerState(
        val buffering: Boolean,
        val currentTrack: PlayableMedia?,
        val duration: Long,
        val playing: Boolean,
        val error: Exception?,
    )

    data class ProgressState(
        val playbackProgress: Long,
        val bufferedProgress: Long,
    )

    fun queue(playableMedia: PlayableMedia)

    fun play(playableMedia: PlayableMedia)

    fun playWhenReady(state: Boolean)

    val playerStateFlow: StateFlow<PlayerState>

    val progressStateFlow: StateFlow<ProgressState>
}