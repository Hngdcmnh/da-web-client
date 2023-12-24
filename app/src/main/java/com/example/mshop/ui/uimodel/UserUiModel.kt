package com.example.mshop.ui.uimodel

import androidx.databinding.ObservableField
import com.example.mshop.domain.entity.auth.Role
import com.example.mshop.domain.entity.user.Gender

data class UserUiModel(
    var userId: String = "",
    val avatar: ObservableField<String> = ObservableField(""),
    val cover: ObservableField<String> = ObservableField(""),
    val fullName: ObservableField<String> = ObservableField(""),
    val gender: ObservableField<String> = ObservableField(Gender.OTHER.value),
    val phone: ObservableField<String> = ObservableField(""),
    val address: AddressUiModel = AddressUiModel(),
    val dob: ObservableField<String> = ObservableField(""),
    val role: ObservableField<String> = ObservableField(Role.CONSUMER.value)
)