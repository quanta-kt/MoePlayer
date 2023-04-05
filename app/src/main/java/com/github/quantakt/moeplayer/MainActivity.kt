package com.github.quantakt.moeplayer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import com.github.quantakt.moeplayer.features.home.Home
import com.github.quantakt.moeplayer.ui.theme.MoePlayerTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoePlayerTheme {
                Surface {
                    Home()
                }
            }
        }
    }
}
