package com.example.mshop.data.dto.discovery

data class ImageDto(
    val imageId: String,
    val url: String,
    val ownerId: String? = null,
)
