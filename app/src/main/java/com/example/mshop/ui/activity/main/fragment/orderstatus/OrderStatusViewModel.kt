package com.example.mshop.ui.activity.main.fragment.orderstatus

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.base_android.ktx.bindUiState
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.example.mshop.domain.entity.order.OrderCartProduct
import com.example.mshop.domain.repository.OrderRepository
import com.example.mshop.ui.activity.main.adapter.OrderStatusAdapter
import com.example.mshop.ui.models.order.OrderStatusTab
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderStatusViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : BaseViewModel<NavDirections>() {

    var error: SingleEvent<String?> = SingleEvent(null)

    private val _orderSuppliers = MutableLiveData<List<OrderCartProduct>>()
    val orderSuppliers: LiveData<List<OrderCartProduct>> = _orderSuppliers

    val orderStatusAdapter = OrderStatusAdapter()

    private val _isRefresh = MutableLiveData(false)
    val isRefresh: LiveData<Boolean> = _isRefresh

    init {
        refresh()
    }

    fun refresh() {
        _isRefresh.value = true
        onClickTabItem(OrderStatusTab.PREPARE)
    }

    private fun getOrderSuppliers(orderStatusTab: String) = launch {
        orderRepository.getOrderByStatus(orderStatusTab)
            .onSuccess { orderCartProducts ->
                orderStatusAdapter.submitList(orderCartProducts)
                _isRefresh.value = false
            }
            .onError {
                error = SingleEvent(it.message)
                _isRefresh.value = false
            }.bindUiState(_uiState)
    }
    fun back() {
        navigator.back()
    }

    fun onClickTabItem(tab: com.example.mshop.ui.models.order.OrderStatusTab) {
        getOrderSuppliers(tab.name)
    }
}