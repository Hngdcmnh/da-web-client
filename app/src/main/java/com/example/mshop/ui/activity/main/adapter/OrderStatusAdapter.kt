package com.example.mshop.ui.activity.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sudo248.base_android.base.BaseListAdapter
import com.sudo248.base_android.base.BaseViewHolder
import com.example.mshop.databinding.ItemOrderStatusBinding
import com.example.mshop.domain.entity.order.OrderStatusCartProduct
import com.example.mshop.ui.uimodel.adapter.loadImage
import com.example.mshop.ui.util.Utils

class OrderStatusAdapter(
    private val onItemClick: (() -> Unit)? = null,
) : BaseListAdapter<OrderStatusCartProduct, OrderStatusAdapter.OrderStatusViewHolder>() {

    inner class OrderStatusViewHolder(binding: ItemOrderStatusBinding) :
        BaseViewHolder<OrderStatusCartProduct, ItemOrderStatusBinding>(binding) {
        override fun onBind(item: OrderStatusCartProduct) {
            binding.apply {
                this.tvName.text = item.product.name
                this.tvCreatedAt.text = Utils.formatReceivedDate(item.createdAt)
                this.tvOrderId.text = "Đơn hàng: #${item.orderId}"
                this.tvPrice.text = Utils.formatVnCurrency(item.product.price)
                loadImage(binding.ivImage, item.product.images[0])
                this.tvQuantity.text = "x${item.quantity}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderStatusViewHolder {
        return OrderStatusViewHolder(
            ItemOrderStatusBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

}