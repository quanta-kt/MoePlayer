package com.github.quantakt.moeplayer.player

import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds

@Singleton
internal class MediaPlayerImpl @Inject constructor(
    private val exoPlayer: ExoPlayer,
    mainScope: CoroutineScope = CoroutineScope(Dispatchers.Main) + Job(),
) : MediaPlayer {

    override fun queue(playableMedia: PlayableMedia) {
        with(exoPlayer) {
            addMediaItem(
                MediaItem.Builder()
                    .setUri(playableMedia.streamUrl)
                    .setTag(playableMedia)
                    .build()
            )
            prepare()
            play()
        }
    }

    override fun play(playableMedia: PlayableMedia) {
        with(exoPlayer) {
            setMediaItem(
                MediaItem.Builder()
                    .setUri(playableMedia.streamUrl)
                    .setTag(playableMedia)
                    .build()
            )

            prepare()
            play()
        }
    }

    override fun playWhenReady(state: Boolean) {
        exoPlayer.playWhenReady = state
    }

    override val playerStateFlow = callbackFlow {
        val listener = createListener { player, events ->
            if (!events.containsAny(
                    Player.EVENT_PLAYBACK_STATE_CHANGED,
                    Player.EVENT_PLAYER_ERROR,
                    Player.EVENT_IS_PLAYING_CHANGED,
                    Player.EVENT_IS_LOADING_CHANGED,
                )
            ) return@createListener

            val state = MediaPlayer.PlayerState(
                buffering = player.playbackState == Player.STATE_BUFFERING,
                currentTrack = player.currentMediaItem?.localConfiguration?.tag as? PlayableMedia,
                duration = player.contentDuration,
                playing = player.isPlaying,
                error = player.playerError
            )

            trySend(state)
        }

        exoPlayer.addListener(listener)
        awaitClose { exoPlayer.removeListener(listener) }

    }.distinctUntilChanged().stateIn(
        mainScope,
        SharingStarted.Eagerly,
        MediaPlayer.PlayerState(
            buffering = false,
            currentTrack = null,
            duration = 0,
            playing = false,
            error = null
        )
    )

    override val progressStateFlow = flow {
        while (true) {
            emit(
                MediaPlayer.ProgressState(
                    playbackProgress = exoPlayer.currentPosition,
                    bufferedProgress = exoPlayer.bufferedPosition
                )
            )

            delay(1.seconds)
        }
    }.distinctUntilChanged().stateIn(
        mainScope,
        SharingStarted.WhileSubscribed(10.seconds),
        MediaPlayer.ProgressState(0, 0)
    )
}

private fun createListener(listener: (Player, Player.Events) -> Unit): Player.Listener {
    return object : Player.Listener {
        override fun onEvents(player: Player, events: Player.Events) {
            listener(player, events)
        }
    }
}
