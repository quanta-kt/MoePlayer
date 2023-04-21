package com.github.quantakt.moeplayer.ui.player

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.github.quantakt.moeplayer.player.MediaPlayer

val LocalMediaPlayer = staticCompositionLocalOf<MediaPlayer> {
    error("CompositionLocal LocalMediaPlayer is not present")
}

@Composable
fun MediaPlayerView(
    modifier: Modifier = Modifier,
) {
    val player = LocalMediaPlayer.current
    val playerState by player.playerStateFlow.collectAsState()
    val progressState by player.progressStateFlow.collectAsState()

    // TODO: Implement player view

    val message = """
        |Progress: ${progressState.playbackProgress}
        |Buffered: ${progressState.bufferedProgress}
        |
        |$playerState
        |""".trimMargin()

    Text(
        modifier = Modifier
            .then(modifier),
        text = message,
    )
}