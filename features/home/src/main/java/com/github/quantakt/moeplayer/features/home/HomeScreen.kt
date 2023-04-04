package com.github.quantakt.moeplayer.features.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Home(
    viewModel: HomeViewModel = viewModel()
) {

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {

        Spacer(Modifier.height(21.dp))

        TextField(
            value = state.query,
            onValueChange = viewModel::setSearchQuery,
            modifier = Modifier.fillMaxWidth(),
        )

        val showResult by remember(state.results) {
            derivedStateOf {
                state.query.isNotBlank() && state.results != null
            }
        }

        AnimatedVisibility(showResult, Modifier) {
            LazyColumn(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
            ) {
                item {
                    Text("Anime")
                }

                items(state.results?.anime.orEmpty(), key = { it.id }) { anime ->
                    SearchItem(
                        modifier = Modifier.fillMaxWidth(),
                        title = anime.title,
                        onClick = { /* TODO */ }
                    )
                }

                item {
                    Text("Themes")
                }

                items(state.results?.animeThemes.orEmpty(), key = { it.id }) { animeTheme ->
                    SearchItem(
                        modifier = Modifier.fillMaxWidth(),
                        title = animeTheme.title,
                        onClick = { /* TODO */ }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchItem(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(title)
        }
    }
}