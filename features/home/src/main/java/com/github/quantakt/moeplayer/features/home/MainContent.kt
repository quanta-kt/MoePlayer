@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.github.quantakt.moeplayer.features.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.quantakt.moeplayer.ui.player.MediaPlayerView

@Composable
internal fun MainContent(modifier: Modifier = Modifier, contentPadding: PaddingValues) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        header()
        recentUploads()

        item {
            MediaPlayerView(modifier = Modifier.fillMaxWidth())
        }
    }
}

private fun LazyListScope.header() {
    item {
        Tile(
            modifier = Modifier.fillMaxWidth(),
            text = "Play Random",
            onClick = {}
        )
    }

    item {
        Tile(
            modifier = Modifier.fillMaxWidth(),
            text = "Current Season",
            onClick = {}
        )
    }

    indexes()
}

private fun LazyListScope.indexes() = item {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Row {
            Tile(
                modifier = Modifier.weight(1f),
                text = "Anime Index",
                onClick = {},
            )

            Spacer(modifier = Modifier.width(8.dp))

            Tile(
                modifier = Modifier.weight(1f),
                text = "Artist Index",
                onClick = {},
            )
        }

        Row {
            Tile(
                modifier = Modifier.weight(1f),
                text = "Studio Index",
                onClick = {},
            )

            Spacer(modifier = Modifier.width(8.dp))

            Tile(
                modifier = Modifier.weight(1f),
                text = "Year Index",
                onClick = {},
            )
        }

        Row {
            Tile(
                modifier = Modifier.weight(1f),
                text = "Events",
                onClick = {},
            )

            Spacer(modifier = Modifier.width(8.dp))

            Tile(
                modifier = Modifier.weight(1f),
                text = "r/Anime Awards",
                onClick = {},
            )
        }
    }
}

private fun LazyListScope.recentUploads() {
    item {
        Spacer(modifier = Modifier.height(8.dp))
        Text("Recent uploads", style = MaterialTheme.typography.headlineSmall)
    }

    items(10) {
        SearchItem(
            modifier = Modifier.fillMaxWidth(),
            imageUrl = "https://animethemes-stag-images.fra1.cdn.digitaloceanspaces.com/anime/large-cover/h13skQLwDH48YnLML9iPmi8YMVYeHU2fICealvdn.png",
            title = "Yofukashi no uta",
            subtitle = "Theme • OP • Yofukashi no uta",
            onClick = {},
        )
    }
}

@Composable
private fun Tile(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        onClick = onClick,
    ) {
        Row(
            Modifier.heightIn(min = 80.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp).align(Alignment.CenterVertically),
            )
        }
    }
}