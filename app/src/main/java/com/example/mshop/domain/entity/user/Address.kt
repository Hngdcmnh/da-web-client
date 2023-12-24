package com.example.mshop.domain.entity.user

data class Address(
    val addressId: String = "",
    val provinceID: Int = 0,
    val districtID: Int = 0,
    val wardCode: String = "",
    val provinceName: String = "",
    val districtName: String = "",
    val wardName: String = "",
    val address: String = "",
    val location: Location? = null,
    val fullAddress: String = "",
)
