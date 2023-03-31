package com.github.quantakt.moeplayer.data.service.animethemesapi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeThemeEntry(
    val id: Long,
    val version: Int? = null,
    val episodes: String? = null,
    val nsfw: Boolean,
    val spoiler: Boolean,
    val notes: String? = null,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("deleted_at")
    val deletedAt: String? = null,

    // relations
    val videos: List<Video>? = null,
)