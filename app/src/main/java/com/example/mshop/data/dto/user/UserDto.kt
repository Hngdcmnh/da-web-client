package com.example.mshop.data.dto.user

import com.example.mshop.domain.entity.auth.Role
import com.example.mshop.domain.entity.user.Gender
import java.time.LocalDate

data class UserDto(
    val userId: String,
    val fullName: String,
    val emailOrPhoneNumber: String,
    val dob: LocalDate,
    val bio: String = "",
    val avatar: String,
    val cover: String = "",
    val address: AddressDto,
    val gender: Gender,
    val role: Role = Role.CONSUMER
)
