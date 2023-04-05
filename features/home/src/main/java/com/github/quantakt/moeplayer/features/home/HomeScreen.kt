@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.quantakt.moeplayer.features.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Home(
    viewModel: HomeViewModel = viewModel()
) {

    val state by viewModel.state.collectAsState()

    val hasQuery by remember(state.results) {
        derivedStateOf {
            state.query.isNotBlank()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {

        Spacer(Modifier.height(21.dp))

        SearchBar(
            query = state.query,
            onQueryChange = viewModel::setSearchQuery,
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
                    IconButton(onClick = { viewModel.setSearchQuery("") }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )

        AnimatedVisibility(hasQuery, Modifier) {
            LazyColumn(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
            ) {
                item {
                    if (!state.results?.anime.isNullOrEmpty()) {
                        Text("Anime")
                    }
                }

                items(state.results?.anime.orEmpty(), key = { it.id }) { anime ->
                    SearchItem(
                        modifier = Modifier.fillMaxWidth(),
                        title = anime.title,
                        subtitle = "Anime • ${anime.season} ${anime.year}",
                        onClick = { /* TODO */ },
                        imageUrl = anime.imageUrl,
                    )
                }

                item {
                    if (!state.results?.animeThemes.isNullOrEmpty()) {
                        Text("Themes")
                    }
                }

                items(state.results?.animeThemes.orEmpty(), key = { it.id }) { animeTheme ->
                    SearchItem(
                        modifier = Modifier.fillMaxWidth(),
                        title = animeTheme.title,
                        subtitle = "Theme • ${animeTheme.type}${animeTheme.sequence} • ${animeTheme.animeTitle}",
                        onClick = { /* TODO */ },
                        imageUrl = animeTheme.imageUrl,
                    )
                }
            }
        }
    }
}
