package com.example.mshop.data.dto.user

import com.example.mshop.domain.entity.user.Location

data class AddressDto(
    val addressId: String,
    val provinceID: Int,
    val districtID: Int,
    val wardCode: String,
    val provinceName: String,
    val districtName: String,
    val wardName: String,
    val address: String,
    val location: Location? = null,
    val fullAddress: String,
)