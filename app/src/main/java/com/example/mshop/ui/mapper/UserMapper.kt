package com.example.mshop.ui.mapper

import androidx.databinding.ObservableField
import com.example.mshop.domain.common.Constants
import com.example.mshop.domain.entity.auth.Role
import com.example.mshop.domain.entity.user.Address
import com.example.mshop.domain.entity.user.Gender
import com.example.mshop.domain.entity.user.Location
import com.example.mshop.domain.entity.user.User
import com.example.mshop.ui.uimodel.AddressUiModel
import com.example.mshop.ui.uimodel.UserUiModel
import com.example.mshop.ui.util.Utils

fun Address.toAddressUi(): AddressUiModel {
    return AddressUiModel(
        addressId,
        provinceID,
        districtID,
        wardCode,
        provinceName = ObservableField(provinceName),
        districtName = ObservableField(districtName),
        wardName = ObservableField(wardName),
        address = ObservableField(address),
        location = location,
        fullAddress = ObservableField(fullAddress)
    )
}

fun AddressUiModel.toAddress(location: Location): Address {
    return Address(
        addressId = addressId,
        provinceID = provinceID,
        districtID = districtID,
        wardCode = wardCode,
        provinceName = provinceName.get().orEmpty(),
        districtName = districtName.get().orEmpty(),
        wardName = wardName.get().orEmpty(),
        address = address.get().orEmpty(),
        location = location,
        fullAddress = ""
    )
}

fun User.toUserUi(): UserUiModel {
    return UserUiModel(
        userId = userId,
        avatar = ObservableField(avatar),
        cover = ObservableField(cover),
        fullName = ObservableField(fullName),
        gender = ObservableField(gender.value),
        phone = ObservableField(phone),
        address = address.toAddressUi(),
        dob = ObservableField(Utils.formatDob(dob)),
        role = ObservableField(role.value)
    )
}

fun UserUiModel.toUser(location: Location? = null): User {
    return User(
        userId = userId,
        avatar = avatar.get() ?: Constants.Images.DEFAULT_USER_IMAGE,
        cover = cover.get().orEmpty(),
        fullName = fullName.get().orEmpty(),
        gender = Gender.fromStringValue(gender.get().orEmpty()),
        phone = phone.get().orEmpty(),
        address = address.toAddress(location ?: Location()),
        dob = Utils.parseDob(dob.get().orEmpty()),
        role = Role.fromStringValue(role.get().orEmpty())
    )
}