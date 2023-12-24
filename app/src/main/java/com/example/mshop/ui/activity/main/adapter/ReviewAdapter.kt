package com.example.mshop.ui.activity.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mshop.R
import com.example.mshop.databinding.ItemNotYetReviewBinding
import com.example.mshop.databinding.ItemReviewedBinding
import com.example.mshop.domain.entity.discovery.ProductInfo
import com.example.mshop.domain.entity.discovery.Review
import com.example.mshop.ui.base.BasePageListAdapter
import com.example.mshop.ui.base.BasePageViewHolder
import com.example.mshop.ui.base.LoadMoreListener
import com.example.mshop.ui.models.review.ReviewListTab
import com.example.mshop.ui.uimodel.adapter.loadImage
import com.example.mshop.ui.util.Utils

class ReviewAdapter(
    private val tab: ReviewListTab,
    onLoadMore: LoadMoreListener? = null,
    private val onClickProduct: ((ProductInfo) -> Unit)? = null,
    private val onClickReview: ((Review) -> Unit)? = null
) : BasePageListAdapter<Review, BasePageViewHolder<Review, *>>() {
    override val enableLoadMore: Boolean = true

    init {
        onLoadMore?.let { setLoadMoreListener(it) }
    }

    override fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): BasePageViewHolder<Review, *> {
        return if (tab == ReviewListTab.REVIEWED) {
            ReviewedViewHolder(ItemReviewedBinding.inflate(layoutInflater, parent, false))
        } else {
            NotYetReviewViewHolder(ItemNotYetReviewBinding.inflate(layoutInflater, parent, false))
        }
    }

    inner class NotYetReviewViewHolder(binding: ItemNotYetReviewBinding) :
        BasePageViewHolder<Review, ItemNotYetReviewBinding>(binding) {
        override fun onBind(item: Review) {
            binding.apply {
                item.productInfo?.images?.let { loadImage(imgProduct, it.first()) }
                txtNameProduct.text = item.productInfo?.name
                txtBrand.text =
                    itemView.context.getString(R.string.product_brand, item.productInfo?.brand)
                txtPrice.text = Utils.formatVnCurrency(item.productInfo?.price ?: 0.0)

                txtReview.setOnClickListener {
                    onClickReview?.invoke(item)
                }
            }
        }

    }

    inner class ReviewedViewHolder(binding: ItemReviewedBinding) :
        BasePageViewHolder<Review, ItemReviewedBinding>(binding) {
        override fun onBind(item: Review) {
            binding.apply {
                loadImage(imgAvatar, item.userInfo.avatar)
                txtNameUser.text = item.userInfo.fullName
                rating.rating = item.rate
                txtCommentTime.text = Utils.formatDateTime(item.updatedAt)
                txtComment.text = item.comment
                item.productInfo?.images?.first()?.let { loadImage(imgProduct, it) }
                txtNameProduct.text = item.productInfo?.name
            }
        }

    }

    val isEmpty
        get() = currentList.isEmpty()

}