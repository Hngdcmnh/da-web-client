package com.example.mshop.domain.entity.order

enum class ShipmentType(val value: String) {
    NOTHING(""),
    EXPRESS("Nhanh"),
    STANDARD("Chuẩn"),
    SAVING("Tiết kiệm")
}