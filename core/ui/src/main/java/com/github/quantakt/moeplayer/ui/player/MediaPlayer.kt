package com.github.quantakt.moeplayer.ui.player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.quantakt.moeplayer.player.MediaPlayer
import com.github.quantakt.moeplayer.player.PlayableMedia
import com.github.quantakt.moeplayer.ui.R
import com.github.quantakt.moeplayer.ui.theme.MoePlayerTheme
import kotlin.math.roundToInt

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

    val showPlayer by remember {
        derivedStateOf {
            playerState.currentTrack != null
        }
    }

    AnimatedVisibility(
        visible = showPlayer,
        enter = slideInVertically { it },
        exit = slideOutVertically { it },
    ) {
        MediaPlayerView(
            modifier = modifier,
            playerState = playerState,
            progressState = progressState,
            togglePlay = { player.playWhenReady(!playerState.playing) }
        )
    }
}

@Composable
private fun MediaPlayerView(
    modifier: Modifier = Modifier,
    playerState: MediaPlayer.PlayerState,
    progressState: MediaPlayer.ProgressState,
    togglePlay: () -> Unit,
) {

    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .then(modifier),
        color = MaterialTheme.colorScheme.primaryContainer,
    ) {
        Column {

            Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                AsyncImage(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    model = playerState.currentTrack?.artUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Text(
                        text = playerState.currentTrack?.title ?: "",
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                    )
                    Spacer(
                        Modifier.height(8.dp)
                    )
                    Text(
                        text = playerState.currentTrack?.animeTitle ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                    )
                }

                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier.padding(12.dp),
                        painter = painterResource(id = R.drawable.ic_player_previous),
                        contentDescription = null
                    )
                }

                IconButton(
                    modifier = Modifier.size(48.dp),
                    onClick = togglePlay,
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null
                    )
                }

                IconButton(
                    onClick = {},
                ) {
                    Icon(
                        modifier = Modifier.padding(12.dp),
                        painter = painterResource(id = R.drawable.ic_player_next),
                        contentDescription = null
                    )
                }
            }

            ProgressBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp),
                progress = progressState.playbackProgress.toFloat() / playerState.duration,
                bufferProgress = progressState.bufferedProgress.toFloat() / playerState.duration
            )
        }
    }
}

@Composable
private fun ProgressBar(
    modifier: Modifier,
    progressColor: Color = MaterialTheme.colorScheme.primary,
    bufferProgressColor: Color = Color.Gray,
    progress: Float,
    bufferProgress: Float,
) {
    Layout(
        modifier = modifier,
        content = {
            Box(modifier = Modifier.background(progressColor))
            Box(modifier = Modifier.background(bufferProgressColor))
        }
    ) { measurables, constraints ->

        val progressBarWidth = (constraints.maxWidth * progress).roundToInt()
        val progressBarPlaceable = measurables.first().measure(
            constraints.copy(
                minWidth = progressBarWidth,
                maxWidth = progressBarWidth
            )
        )

        val bufferProgressBarWidth = (constraints.maxWidth * bufferProgress).roundToInt()
        val bufferProgressBarPlaceable = measurables.last().measure(
            constraints.copy(
                minWidth = bufferProgressBarWidth,
                maxWidth = bufferProgressBarWidth
            )
        )

        layout(constraints.maxWidth, constraints.maxHeight) {
            bufferProgressBarPlaceable.place(0, 0)
            progressBarPlaceable.place(0, 0)
        }
    }
}

@Preview
@Composable
private fun ProgressBarPreview() {
    MoePlayerTheme {
        ProgressBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp),
            progress = .4f,
            bufferProgress = .5f
        )
    }
}

@Preview
@Composable
private fun PlayerPreview() {
    MoePlayerTheme {
        MediaPlayerView(
            modifier = Modifier.fillMaxWidth(),
            playerState = MediaPlayer.PlayerState(
                buffering = false,
                currentTrack = PlayableMedia(
                    title = "ふわふわ時間",
                    animeTitle = "K-On",
                    streamUrl = "",
                    artUrl = null
                ),
                100,
                true,
                null,
            ),
            progressState = MediaPlayer.ProgressState(
                playbackProgress = 40,
                bufferedProgress = 50,
            ),
            togglePlay = {}
        )
    }
}