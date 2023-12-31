@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.quantakt.moeplayer.features.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.quantakt.moeplayer.model.SearchResult
import com.github.quantakt.moeplayer.player.PlayableMedia
import com.github.quantakt.moeplayer.ui.player.LocalMediaPlayer
import kotlinx.coroutines.launch

@Composable
fun Home(
    modifier: Modifier,
    viewModel: HomeViewModel = viewModel()
) {

    val state by viewModel.state.collectAsState()

    val player = LocalMediaPlayer.current

    val scope = rememberCoroutineScope()

    Home(
        modifier = modifier,
        state = state,
        setSearchQuery = viewModel::setSearchQuery,
        playTrack = { animeTheme ->
            scope.launch {
                val streamUrl = viewModel.getAudioUrl(animeTheme)

                player.play(
                    PlayableMedia(
                        streamUrl = streamUrl,
                        artUrl = animeTheme.imageUrl,
                        title = animeTheme.title,
                        animeTitle = animeTheme.animeTitle,
                    )
                )
            }
        }
    )
}

@Composable
private fun Home(
    modifier: Modifier,
    state: HomeScreenState,
    setSearchQuery: (query: String) -> Unit,
    playTrack: (theme: SearchResult.AnimeTheme) -> Unit,
) {

    val hasQuery by remember(state.results) {
        derivedStateOf {
            state.query.isNotBlank()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
    ) {

        Spacer(Modifier.height(16.dp))

        SearchBar(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            query = state.query,
            onQueryChange = setSearchQuery,
            onSearch = {},
            active = false,
            onActiveChange = {},
            content = {},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                )
            },
            trailingIcon = {
                if (hasQuery) {
                    IconButton(onClick = { setSearchQuery("") }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                        )
                    }
                }
            },
        )

        AnimatedVisibility(!hasQuery) {
            MainContent(contentPadding = PaddingValues(16.dp))
        }

        AnimatedVisibility(hasQuery) {
            SearchResults(
                result = state.results,
                contentPadding = PaddingValues(16.dp),
                playTrack = playTrack
            )
        }
    }
}
