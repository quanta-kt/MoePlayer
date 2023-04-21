package com.github.quantakt.moeplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.github.quantakt.moeplayer.features.home.Home
import com.github.quantakt.moeplayer.player.MediaPlayer
import com.github.quantakt.moeplayer.ui.player.LocalMediaPlayer
import com.github.quantakt.moeplayer.ui.player.MediaPlayerView
import com.github.quantakt.moeplayer.ui.theme.MoePlayerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var player: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(
                LocalMediaPlayer provides player
            ) {
                MoePlayerTheme {
                    App()
                }
            }
        }
    }
}

@Composable
private fun App() {
    Scaffold(
        bottomBar = {
            MediaPlayerView()
        }
    ) {
        Home(modifier = Modifier.padding(it))
    }

}