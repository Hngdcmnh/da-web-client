package com.example.mshop.ui.activity.auth.fragment.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.ktx.createActionIntentDirections
import com.sudo248.base_android.ktx.onState
import com.example.mshop.BuildConfig
import com.example.mshop.domain.common.Constants
import com.example.mshop.domain.repository.AuthRepository
import com.example.mshop.ui.activity.auth.AuthViewModel
import com.example.mshop.ui.mapper.toAccount
import com.example.mshop.ui.uimodel.AccountUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 09:10 - 06/03/2023
 */
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel<NavDirections>() {
    private var parentViewModel: AuthViewModel? = null

    val accountUiModel: AccountUiModel = AccountUiModel()
    val confirmPassword: MutableLiveData<String> = MutableLiveData()

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    lateinit var gotoSignIn: () -> Unit

    fun setParentViewModel(viewModel: AuthViewModel) {
        parentViewModel = viewModel
    }

    fun signUp() = launch {
        parentViewModel?.setState(UiState.LOADING)
        val account = accountUiModel.toAccount()
        authRepository.signUp(account).onState(
            onSuccess = {
                parentViewModel?.setState(UiState.SUCCESS)

                    gotoSignIn()
            },
            onError = {
                _error.postValue(it.message)
                accountUiModel.password.set("")
                confirmPassword.postValue("")
                parentViewModel?.setState(UiState.ERROR)
            }
        )
    }

    fun onConfirmPasswordTextChange(text: CharSequence) {
        if (text.toString() != accountUiModel.password.get()) {
            _error.postValue("Incorrect password")
        } else {
            _error.postValue("")
        }
    }
}