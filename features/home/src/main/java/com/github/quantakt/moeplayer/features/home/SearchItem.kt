package com.github.quantakt.moeplayer.features.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchItem(
    modifier: Modifier = Modifier,
    imageUrl: String?,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
) {

    Card(
        modifier = modifier,
        onClick = onClick,
    ) {

        Row(
            Modifier.height(IntrinsicSize.Min)
        ) {

            if (imageUrl != null) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier.fillMaxHeight(),
                    contentScale = ContentScale.FillHeight
                )
            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(title, maxLines = 1)

                Spacer(Modifier.height(8.dp))

                Text(
                    subtitle,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}