package com.example.mshop.ui.activity.main.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mshop.data.dto.order.OrderDto
import com.example.mshop.databinding.ItemBuyedOrderBinding
import com.example.mshop.databinding.ItemOrderStatusBinding
import com.example.mshop.domain.entity.order.OrderStatusCartProduct
import com.example.mshop.ui.uimodel.adapter.loadImage
import com.example.mshop.ui.util.Utils
import com.sudo248.base_android.base.BaseListAdapter
import com.sudo248.base_android.base.BaseViewHolder

class BuyedOrderAdapter(
    private val onItemClick: (() -> Unit)? = null,
) : BaseListAdapter<OrderDto, BuyedOrderAdapter.BuyedOrderViewHolder>() {

    inner class BuyedOrderViewHolder(binding: ItemBuyedOrderBinding) :
        BaseViewHolder<OrderDto, ItemBuyedOrderBinding>(binding) {
        override fun onBind(item: OrderDto) {
            binding.apply {

                this.tvCreatedAt.text = Utils.formatDateTime(item.createdAt)
                this.tvOrderId.text = "#${item.orderId}"
                when (item.status) {
                    "RECEIVED" -> {
                        this.tvStatus.setBackgroundColor(Color.parseColor("#42f54e"))
                    }
                    "PREPARE" -> {
                        this.tvStatus.setBackgroundColor(Color.parseColor("#f5e042"))
                    }
                    else -> {
                        this.tvStatus.setBackgroundColor(Color.parseColor("#FF792E"))
                    }
                }
                this.tvStatus.text = item.status
                this.tvPrice.text = Utils.formatVnCurrency(item.finalPrice)
//                loadImage(binding.ivImage, item.product.images[0])
//                this.tvQuantity.text = "x${item.quantity}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyedOrderViewHolder {
        return BuyedOrderViewHolder(
            ItemBuyedOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

}