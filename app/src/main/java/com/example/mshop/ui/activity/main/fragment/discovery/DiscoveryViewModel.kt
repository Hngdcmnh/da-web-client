package com.example.mshop.ui.activity.main.fragment.discovery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import com.example.mshop.R
import com.example.mshop.domain.entity.cart.AddCartProduct
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.event.SingleEvent
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.example.mshop.domain.entity.discovery.Category
import com.example.mshop.domain.entity.discovery.Offset
import com.example.mshop.domain.entity.discovery.ProductInfo
import com.example.mshop.domain.repository.CartRepository
import com.example.mshop.domain.repository.DiscoveryRepository
import com.example.mshop.ui.activity.main.adapter.CategoryAdapter
import com.example.mshop.ui.activity.main.adapter.ProductInfoAdapter
import com.example.mshop.ui.activity.main.fragment.product_detail.ViewController
import com.example.mshop.ui.base.EndlessNestedScrollListener
import com.sudo248.base_android.core.UiState
import com.sudo248.base_android.ktx.bindUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DiscoveryViewModel @Inject constructor(
    private val discoveryRepository: DiscoveryRepository,
    private val cartRepository: CartRepository
) : BaseViewModel<NavDirections>() {

    var viewController: ViewController? = null
    private var nextOffset: Offset = Offset()

    private val _isRefresh = MutableLiveData(false)
    val isRefresh: LiveData<Boolean> = _isRefresh

    var error: SingleEvent<String?> = SingleEvent(null)

    val categoryAdapter = CategoryAdapter(::onCategoryItemClick)

    val productInfoAdapter = ProductInfoAdapter( onItemClick = ::onProductItemClick, onAddToCartClick = ::onAddToCartClick)

    val endlessNestedScrollListener = object : EndlessNestedScrollListener() {
        override fun onLoadMore() {
            loadMoreProduct()
        }
    }

    init {
        refresh()
    }

    fun refresh() {
        _isRefresh.value = true
        getCategories()
        getRecommendProductList()
    }

    private fun getCategories() = launch {
        discoveryRepository.getCategories()
            .onSuccess { categories ->
                categoryAdapter.submitList(categories)
                _isRefresh.value = false
            }
            .onError {
                error = SingleEvent(it.message)
                _isRefresh.value = false
            }
    }


    private fun getRecommendProductList(isLoadMore: Boolean = false) = launch {
        if (!isLoadMore) {
            nextOffset.reset()
        }
        discoveryRepository.getRecommendProductList(nextOffset)
            .onSuccess {
                if (it.products.size < nextOffset.limit) {
                    productInfoAdapter.isLastPage(true)
                    endlessNestedScrollListener.isLastPage(true)
                } else {
                    nextOffset.offset = it.pagination.offset
                }
                productInfoAdapter.submitData(it.products, extend = isLoadMore)
            }
            .onError {
                error = SingleEvent(it.message)
            }
    }

    private fun onCategoryItemClick(item: Category) {
        navigator.navigateTo(
            DiscoveryFragmentDirections.actionDiscoveryFragmentToSearchFragment(
                categoryId = item.categoryId,
                categoryName = item.name
            )
        )
    }

    private fun onProductItemClick(item: ProductInfo) {
        navigateToProductDetail(item.productId)
    }

    private fun onAddToCartClick(item: ProductInfo) {
        launch {
            getCartProduct(item)?.let { cartProduct ->
                setState(UiState.LOADING)
                cartRepository.addProductToActiveCart(cartProduct)
                    .onSuccess { cart ->
                        viewController?.toast(R.string.add_to_cart_success)
                    }
                    .onError {
                        error = SingleEvent(it.message)
                    }.bindUiState(_uiState)
            }
        }
    }

    private fun getCartProduct(item:ProductInfo): AddCartProduct? {
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

    fun loadMoreProduct() {
        getRecommendProductList(isLoadMore = true)
    }

    fun navigateToSearchView() {
        navigator.navigateTo(
            DiscoveryFragmentDirections.actionDiscoveryFragmentToSearchFragment()
        )
    }

    fun navigateToProductDetail(productId: String) {
        navigator.navigateTo(
            DiscoveryFragmentDirections.actionDiscoveryFragmentToProductDetailFragment(
                productId
            )
        )
    }
}