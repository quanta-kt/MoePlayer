package com.github.quantakt.moeplayer.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.quantakt.moeplayer.model.SearchResult

@Composable
internal fun SearchResults(
    result: SearchResult?,
    contentPadding: PaddingValues,
) {

    LazyColumn(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = contentPadding,
    ) {
        item {
            if (!result?.anime.isNullOrEmpty()) {
                Text("Anime")
            }
        }

        items(result?.anime.orEmpty(), key = { it.id }) { anime ->
            SearchItem(
                modifier = Modifier.fillMaxWidth(),
                title = anime.title,
                subtitle = "Anime • ${anime.season} ${anime.year}",
                onClick = { /* TODO */ },
                imageUrl = anime.imageUrl,
            )
        }

        item {
            if (!result?.animeThemes.isNullOrEmpty()) {
                Text("Themes")
            }
        }

        items(result?.animeThemes.orEmpty(), key = { it.id }) { animeTheme ->
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