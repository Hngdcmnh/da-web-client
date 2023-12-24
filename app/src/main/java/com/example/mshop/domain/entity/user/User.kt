package com.example.mshop.domain.entity.user

import com.example.mshop.domain.entity.auth.Role
import java.time.LocalDate

data class User(
    val userId: String = "",
    val avatar: String = "",
    val cover: String = "",
    val fullName: String = "",
    val gender: Gender = Gender.OTHER,
    val phone: String = "",
    val address: Address = Address(),
    val dob: LocalDate = LocalDate.now(),
    val role: Role = Role.CONSUMER
)
