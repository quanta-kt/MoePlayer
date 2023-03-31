package com.github.quantakt.moeplayer.data.service.animethemesapi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Audio(
    val id: Int,
    val basename: String,
    val filename: String,
    val path: String,
    val size: Int,
    val mimetype: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("deleted_at")
    val deletedAt: String? = null,
    val link: String,
)