package com.github.quantakt.moeplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
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
            Column {
                MediaPlayerView()

                NavigationBar {
                    NavigationBarItem(
                        selected = true,
                        onClick = {},
                        icon = {
                            Icon(Icons.Default.Home, contentDescription = null)
                        }
                    )

                    NavigationBarItem(
                        selected = false,
                        onClick = {},
                        icon = {
                            Icon(Icons.Default.List, contentDescription = null)
                        }
                    )
                }
            }
        }
    ) {
        Home(modifier = Modifier.padding(it))
    }

}