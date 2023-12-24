package com.example.mshop.ui.mapper

import androidx.databinding.ObservableField
import com.example.mshop.domain.entity.auth.Account
import com.example.mshop.ui.uimodel.AccountUiModel


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 11:08 - 12/03/2023
 */

fun Account.toAccountUi(): AccountUiModel {
    return AccountUiModel(
        phoneNumber = ObservableField(phoneNumber),
        password = ObservableField(password)
    )
}

fun AccountUiModel.toAccount(): Account {
    return Account(
        phoneNumber = phoneNumber.get() ?: "",
        password = password.get() ?: ""
    )
}