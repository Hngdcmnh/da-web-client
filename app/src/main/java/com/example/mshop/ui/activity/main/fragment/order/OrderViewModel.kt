package com.example.mshop.ui.activity.main.fragment.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.base_android.ktx.bindUiState
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.example.mshop.domain.entity.order.Order
import com.example.mshop.domain.entity.payment.Payment
import com.example.mshop.domain.entity.payment.PaymentStatus
import com.example.mshop.domain.entity.payment.PaymentType
import com.example.mshop.domain.repository.OrderRepository
import com.example.mshop.domain.repository.PaymentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val paymentRepository: PaymentRepository,
    private val orderRepository: OrderRepository
) : BaseViewModel<NavDirections>() {
    var viewController: ViewController? = null

    private val _userInfo = MutableLiveData<String>()
    val userInfo: LiveData<String> = _userInfo

    private val _currentPaymentType = MutableLiveData<PaymentType>()
    val currentPaymentType: LiveData<PaymentType> = _currentPaymentType

    private val _order = MutableLiveData<Order>()
    val order: LiveData<Order> = _order



    private val _finalPrice = MutableLiveData<Double>()
    val finalPrice: LiveData<Double> = _finalPrice

    var error: SingleEvent<String?> = SingleEvent(null)

    var currentSelectedPromotionId: String? = null
        private set


    fun onSelectPaymentType(type: PaymentType) {
        _currentPaymentType.postValue(type)
    }

    fun onPayment() {
        when (_currentPaymentType.value) {
            null -> {
                viewController?.toast("Hãy chọn phương thức thanh toán")
            }

            PaymentType.VN_PAY -> {
                viewController?.openVnPaySdk()
            }

            else -> {
                payWithCOD()
            }
        }
    }

    fun createOrder(cartId: String) = launch {
        if (order.value != null) return@launch
        emitState(UiState.LOADING)
        orderRepository.createOrder(cartId)
            .onSuccess {
                _userInfo.postValue("${it.user.fullName} | ${it.user.phone}\n${it.address}")
                _order.postValue(it)
                _finalPrice.postValue(it.finalPrice)
            }
            .onError {
                error = SingleEvent(it.message)
            }.bindUiState(_uiState)
    }

    suspend fun getVnPayPaymentUrl(): Deferred<String?> = async {
        emitState(UiState.LOADING)
        val response = paymentRepository.pay(
            Payment(
                paymentType = PaymentType.VN_PAY,
                orderId = _order.value!!.orderId,
                paymentStatus = PaymentStatus.INIT,
                amount = _order.value!!.finalPrice
            )
        )
        when (response) {
            is DataState.Success -> {
                emitState(UiState.SUCCESS)
                response.requireData().paymentUrl
            }

            else -> {
                error = SingleEvent(response.error().message)
                emitState(UiState.ERROR)
                null
            }
        }
    }

    private fun payWithCOD() = launch {
        setState(UiState.LOADING)
        paymentRepository.pay(
            Payment(
                paymentType = PaymentType.CASH,
                orderId = _order.value!!.orderId,
                paymentStatus = PaymentStatus.INIT,
                amount = _order.value!!.finalPrice
            )
        ).onSuccess {
            setState(UiState.SUCCESS)
            viewController?.payWithCODSuccess()
        }.onError {
            error = SingleEvent(it.message)
            setState(UiState.ERROR)
            viewController?.payWithCODError()
        }
    }

    fun back() = launch {
        order.value?.let {
            orderRepository.cancelOrderById(it.orderId)
        }
        navigator.back()
    }
}