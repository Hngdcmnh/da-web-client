package com.example.mshop.ui.activity.splash

import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.ktx.createActionIntentDirections
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.sudo248.base_android.navigation.IntentDirections
import com.example.mshop.domain.repository.AuthRepository
import com.example.mshop.ui.activity.auth.AuthActivity
import com.example.mshop.ui.activity.main.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 08:12 - 06/03/2023
 */
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountRepository: AuthRepository,
) : BaseViewModel<IntentDirections>() {

    init {
        launch {
            accountRepository.refreshToken()
                .onSuccess {
                    navigator.navigateOff(MainActivity::class.createActionIntentDirections())
                }
                .onError {
                    navigator.navigateOff(AuthActivity::class.createActionIntentDirections())
                }
            delay(500)
//        }

//            navigator.navigateOff(OtpActivity::class.createActionIntentDirections{
//                putExtra(Constants.Key.PHONE_NUMBER, "0989465270")
//            })
//            navigator.navigateOff(MainActivity::class.createActionIntentDirections())
//            navigator.navigateOff(PaymentActivity::class.createActionIntentDirections())
        }
    }

}