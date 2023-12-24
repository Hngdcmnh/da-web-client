package com.example.mshop.ui.activity.main.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mshop.databinding.ItemProductBinding
import com.example.mshop.domain.entity.discovery.ProductInfo
import com.example.mshop.ui.base.BasePageListAdapter
import com.example.mshop.ui.base.BasePageViewHolder
import com.example.mshop.ui.base.LoadMoreListener
import com.example.mshop.ui.uimodel.adapter.loadImage
import com.example.mshop.ui.util.Utils



class ProductInfoAdapter(
    private val onAddToCartClick: (ProductInfo) -> Unit,
    private val onItemClick: (ProductInfo) -> Unit,
    onLoadMore: LoadMoreListener? = null,
) : BasePageListAdapter<ProductInfo, ProductInfoAdapter.ProductInfoViewHolder>() {
    override val enableLoadMore: Boolean = true

    init {
        onLoadMore?.let { setLoadMoreListener(it) }
    }

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BasePageViewHolder<ProductInfo, *> {
        return ProductInfoViewHolder(ItemProductBinding.inflate(layoutInflater))
    }

    inner class ProductInfoViewHolder(binding: ItemProductBinding) :
        BasePageViewHolder<ProductInfo, ItemProductBinding>(binding) {
        override fun onBind(item: ProductInfo) {
            binding.apply {
                loadImage(imgProduct, item.images.first())
                tvColor.setBackgroundColor(Color.parseColor(item.color))
                tvSize.text = "Size ${item.size}"
                nameProduct.text = item.name
                txtPrice.text = Utils.formatVnCurrency(item.price)

                if (item.soldAmount <= 0) {
                    rating.rating = 0.0f
                } else {
                    rating.rating = item.rate
                }
                tvSoldAmount.text = "(${item.soldAmount})"

                ivAddToCart.setOnClickListener {
                    onAddToCartClick(item)
                }
                imgcvProduct.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }
}