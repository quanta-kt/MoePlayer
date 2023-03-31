package com.github.quantakt.moeplayer.data.service.animethemesapi.models.resources

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val id: Long,
    val basename: String,
    val filename: String,
    val path: String,
    val size: Int,
    val mimetype: String,
    val resolution: Int?,
    val nc: Boolean,
    val subbed: Boolean,
    val lyrics: Boolean,
    @SerialName("uncen")
    val uncensored: Boolean,
    val source: String? = null,
    val overlap: String? = null,
    val tags: String? = null,
    val link: String? = null,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("deleted_at")
    val deletedAt: String? = null,

    // relations
    val audio: Audio? = null,
)