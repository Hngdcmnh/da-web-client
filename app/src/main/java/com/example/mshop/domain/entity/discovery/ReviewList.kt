package com.example.mshop.domain.entity.discovery

data class ReviewList(
    val reviews: List<Review>,
    val pagination: Pagination,
)