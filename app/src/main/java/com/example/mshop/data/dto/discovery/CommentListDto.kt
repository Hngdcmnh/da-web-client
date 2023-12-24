package com.example.mshop.data.dto.discovery

data class CommentListDto(
    val comments: List<CommentDto>,
    val pagination: PaginationDto,
)
