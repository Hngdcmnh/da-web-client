package com.example.mshop.ui.activity.main.fragment.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.example.mshop.R
import com.example.mshop.domain.entity.cart.AddCartProduct
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.base_android.ktx.bindUiState
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.example.mshop.domain.entity.discovery.Offset
import com.example.mshop.domain.entity.discovery.ProductInfo
import com.example.mshop.domain.repository.CartRepository
import com.example.mshop.domain.repository.DiscoveryRepository
import com.example.mshop.ui.activity.main.adapter.ProductInfoAdapter
import com.example.mshop.ui.activity.main.fragment.product_detail.ViewController
import com.example.mshop.ui.base.LoadMoreListener
import com.sudo248.base_android.core.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val discoveryRepository: DiscoveryRepository,
    private val cartRepository: CartRepository
) : BaseViewModel<NavDirections>() {


    var viewController: ViewController? = null
    private val _isEmptyProduct = MutableLiveData(false)
    val isEmptyProduct: LiveData<Boolean> = _isEmptyProduct

    private val _isRefresh = MutableLiveData(false)
    val isRefresh: LiveData<Boolean> = _isRefresh

    var error: SingleEvent<String?> = SingleEvent(null)

    val productInfoAdapter =
        ProductInfoAdapter(::onAddToCartClick, ::onProductItemClick, object : LoadMoreListener {
            override fun onLoadMore() {
                loadMoreProduct()
            }
        })

    private var nextOffset: Offset = Offset()

    private var categoryId: String? = null

    private var currentSearchName: String = ""

    private var jobSearch: Job? = null

    fun search(name: String) {
        jobSearch?.cancel()
        jobSearch = launch {
            delay(200)
            currentSearchName = name
            performProductListByCategoryIdAndName(
                categoryId = categoryId,
                name = currentSearchName,
                isLoadMore = false,
            )
        }
    }

    fun actionSearch(name: String) {
        jobSearch?.cancel()
        currentSearchName = name
        performProductListByCategoryIdAndName(
            categoryId = categoryId,
            name = currentSearchName,
            isLoadMore = false,
        )
    }

    fun refresh() {
        _isRefresh.value = true
        performProductListByCategoryIdAndName(
            categoryId = categoryId,
            name = currentSearchName,
            isLoadMore = false,
        )
    }

    fun setCategoryId(categoryId: String?) {
        if (categoryId == null) return
        this.categoryId = categoryId
        performProductListByCategoryIdAndName(categoryId = categoryId, isLoadMore = false)
    }

    private fun performProductListByCategoryIdAndName(
        categoryId: String? = null,
        name: String? = null,
        isLoadMore: Boolean = false
    ) = launch {
        if (!isLoadMore) {
            nextOffset.reset()
        }
        discoveryRepository.getProductList(
            categoryId = categoryId.orEmpty(),
            name = name.orEmpty(),
            offset = nextOffset
        )
            .onSuccess {
                if (it.products.size < nextOffset.limit) {
                    productInfoAdapter.isLastPage(true)
                } else {
                    nextOffset.offset = it.pagination.offset
                }
                productInfoAdapter.submitData(it.products, extend = isLoadMore)
            }
            .onError {
                error = SingleEvent(it.message)
            }.bindUiState(_uiState)
        if (_isRefresh.value == true) _isRefresh.value = false
    }

    fun loadMoreProduct() {
        performProductListByCategoryIdAndName(
            categoryId = categoryId,
            name = currentSearchName,
            isLoadMore = true,
        )
    }

    private fun onProductItemClick(item: ProductInfo) {
        navigateToProductDetail(item.productId)
    }

    fun navigateToProductDetail(productId: String) {
        navigator.navigateTo(
            SearchFragmentDirections.actionSearchFragmentToProductDetailFragment(
                productId
            )
        )
    }

    private fun onAddToCartClick(item: ProductInfo) {
        launch {
            getCartProduct(item)?.let { cartProduct ->
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
    }

    private fun getCartProduct(item: ProductInfo): AddCartProduct? {
        return try {
            AddCartProduct(
                productId = item.productId,
                amount = 1
            )
        } catch (e: NullPointerException) {
            error = SingleEvent("Some thing wet wrong")
            setState(UiState.ERROR)
            null
        }
    }

}