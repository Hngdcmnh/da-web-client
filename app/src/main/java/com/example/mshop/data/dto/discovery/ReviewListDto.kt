package com.example.mshop.data.dto.discovery

data class ReviewListDto(
    val reviews: List<ReviewDto>,
    val pagination: PaginationDto,
)