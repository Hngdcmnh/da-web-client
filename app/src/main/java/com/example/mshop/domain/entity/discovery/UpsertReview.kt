package com.example.mshop.domain.entity.discovery

data class UpsertReview(
    val reviewId: String,
    val rate: Float,
    val comment: String,
    var images: List<String>? = null,
)