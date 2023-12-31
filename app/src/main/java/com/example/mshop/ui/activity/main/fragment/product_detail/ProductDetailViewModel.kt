package com.example.mshop.ui.activity.main.fragment.product_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.base_android.ktx.bindUiState
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.example.mshop.R
import com.example.mshop.domain.entity.cart.AddCartProduct
import com.example.mshop.domain.entity.discovery.Offset
import com.example.mshop.domain.entity.discovery.Product
import com.example.mshop.domain.entity.discovery.SupplierInfo
import com.example.mshop.domain.repository.CartRepository
import com.example.mshop.domain.repository.DiscoveryRepository
import com.example.mshop.ui.activity.main.adapter.CommentAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 15:52 - 20/03/2023
 */
@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val discoveryRepository: DiscoveryRepository,
    private val cartRepository: CartRepository
) : BaseViewModel<NavDirections>() {

    var viewController: ViewController? = null

    var error: SingleEvent<String?> = SingleEvent(null)

    private val _isRefresh: MutableLiveData<Boolean> = MutableLiveData(false)
    val isRefresh: LiveData<Boolean> = _isRefresh

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    private val _supplier = MutableLiveData<SupplierInfo>()
    val supplier: LiveData<SupplierInfo> = _supplier

    private val _totalComment: MutableLiveData<Int> = MutableLiveData(0)
    val totalComment: LiveData<Int> = _totalComment

    private val _remainComment: MutableLiveData<Int> = MutableLiveData(0)
    val remainComment: LiveData<Int> = _remainComment

    val commentAdapter: CommentAdapter by lazy { CommentAdapter() }

    private var nextOffset: Offset = Offset()

    fun refresh() {
        _isRefresh.value = true
        _product.value?.let {
            getProduct(it.productId)
        }
    }

    fun getProduct(productId: String) = launch {
        if (_isRefresh.value == false) emitState(UiState.LOADING)
        Log.i("product: ","1")
        discoveryRepository.getProductDetail(productId)
            .onSuccess {
                Log.i("product: ","2")
                _product.postValue(it)
                it.supplier?.let { supplierInfo ->
                    _supplier.value = supplierInfo
                }
                getComments(it.productId)
            }
            .onError {
                Log.i("product: ","${it.message}")
                error = SingleEvent(it.message)
            }.run {
                Log.i("product: ","4")
                if (_isRefresh.value == true) bindUiState(_uiState)
            }
        Log.i("product: ","5")
        _isRefresh.value = false
    }

    private fun getComments(productId: String, isLoadMore: Boolean = false) = launch {
        if (!isLoadMore) {
            nextOffset.reset()
        }
        discoveryRepository.getCommentsOfProduct(productId, offset = nextOffset)
            .onSuccess {
                if (it.comments.size < nextOffset.limit) {
                    _remainComment.value = 0
                } else {
                    nextOffset.offset = it.pagination.offset
                    _remainComment.value =
                        it.pagination.total - (commentAdapter.currentList.size + nextOffset.limit)
                }
                _totalComment.value = it.pagination.total
                commentAdapter.submitData(it.comments, extend = isLoadMore)
            }
            .onError {
                error = SingleEvent(it.message)
            }
    }

    fun countItemInCart() = launch {
        cartRepository.countItemInActiveCart()
            .onSuccess {
                viewController?.setBadgeCart(it)
            }
            .onError {
                error = SingleEvent(it.message)
            }.bindUiState(_uiState)

    }

    fun addProductToCart() = launch {
        getCartProduct()?.let { cartProduct ->
            setState(UiState.LOADING)
            cartRepository.addProductToActiveCart(cartProduct)
                .onSuccess { cart ->
                    viewController?.toast(R.string.add_to_cart_success)
                    viewController?.setBadgeCart(cart.quantity)
                }
                .onError {
                    error = SingleEvent(it.message)
                }.bindUiState(_uiState)
        }
    }

    private fun getCartProduct(): AddCartProduct? {
        return try {
            AddCartProduct(
                productId = _product.value!!.productId,
                amount = 1
            )
        } catch (e: NullPointerException) {
            error = SingleEvent("Some thing wet wrong")
            setState(UiState.ERROR)
            null
        }
    }

    fun canAddToCart(): Boolean {
        return product.value != null && product.value!!.saleable && product.value!!.amount > 0
    }

    fun onBack() {
        navigator.back()
    }

    fun navigateToCart() {
        navigator.navigateTo(ProductDetailFragmentDirections.actionProductDetailFragmentToCartFragment())
    }

    fun navigateToHome() {
        navigator.navigateOffAll(ProductDetailFragmentDirections.actionProductDetailFragmentToDiscoveryFragment())
    }

    fun buyNow() = launch {
        if (canAddToCart()) {
            addProductToCart().join()
        }
//        navigator.navigateOff(ProductDetailFragmentDirections.actionProductDetailFragmentToCartFragment())
    }


    fun getDeeplinkToProduction(): String {
        return ""
    }

    override fun onCleared() {
        super.onCleared()
        viewController = null
    }

}