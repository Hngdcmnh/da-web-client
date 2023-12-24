package com.example.mshop.ui.activity.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sudo248.base_android.base.BaseListAdapter
import com.sudo248.base_android.base.BaseViewHolder
import com.example.mshop.R
import com.example.mshop.databinding.ItemOrderBinding
import com.example.mshop.domain.entity.order.OrderSupplier
import com.example.mshop.ui.util.Utils

class OrderAdapter : BaseListAdapter<OrderSupplier, OrderAdapter.OrderSupplierViewHolder>() {

    inner class OrderSupplierViewHolder(binding: ItemOrderBinding) :
        BaseViewHolder<OrderSupplier, ItemOrderBinding>(binding) {
        override fun onBind(item: OrderSupplier) {
            binding.apply {
                shipment.apply {
                    txtShipmentType.text = item.shipment.shipmentType.value
                    txtShipmentPrice.text = Utils.formatVnCurrency(item.shipment.shipmentPrice)
                    txtReceivedDate.text = itemView.context.getString(
                        R.string.received_date,
                        Utils.formatReceivedDate(item.expectedReceiveDateTime)
                    )
                }
                totalPrice.apply {
                    txtSumProduct.text = itemView.context.getString(
                        R.string.total_money_format,
                        "${item.orderCartProducts.size}"
                    )
                    txtSumPrice.text = Utils.formatVnCurrency(item.totalPrice)
                }
                rcvOrderProducts.adapter = OrderProductAdapter(item.orderCartProducts)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderSupplierViewHolder {
        return OrderSupplierViewHolder(
            ItemOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}