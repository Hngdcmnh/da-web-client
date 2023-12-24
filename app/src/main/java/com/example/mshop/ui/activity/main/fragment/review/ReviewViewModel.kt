package com.example.mshop.ui.activity.main.fragment.review

import android.os.Bundle
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.base_android.ktx.bindUiState
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.example.mshop.domain.repository.DiscoveryRepository
import com.example.mshop.ui.util.BundleKeys
import com.sudo248.base_android.ktx.onState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    private val discoveryRepository: DiscoveryRepository,
) : BaseViewModel<NavDirections>() {

    private var viewController: ViewController? = null

    var error: SingleEvent<String?> = SingleEvent(null)

    fun setViewController(viewController: ViewController) {
        this.viewController = viewController
    }

    fun upsertReview() = launch(Dispatchers.Main) {
        viewController?.getUpsertReview()?.let { upserReview ->
            emitState(UiState.LOADING)

            discoveryRepository.upsertReview(upserReview)
                .onSuccess {
                    navigator.back(BundleKeys.REVIEW_FRAGMENT_KEY, Bundle().apply {
                        putBoolean(BundleKeys.NEED_RELOAD, true)
                    })
                }
                .onError {
                    error = SingleEvent(it.message)
                }.bindUiState(_uiState)
        }
    }

    fun back() {
        navigator.back()
    }

    override fun onCleared() {
        super.onCleared()
        viewController = null
    }
}