package com.example.mshop.data.mapper

import com.example.mshop.data.dto.order.OrderCartProductDto
import com.example.mshop.data.dto.order.OrderDto
import com.example.mshop.data.dto.order.OrderProductInfoDto
import com.example.mshop.data.dto.order.OrderSupplierDto
import com.example.mshop.data.dto.order.UpsertOrderPromotionDto
import com.example.mshop.domain.entity.order.Order
import com.example.mshop.domain.entity.order.OrderCartProduct
import com.example.mshop.domain.entity.order.OrderProductInfo
import com.example.mshop.domain.entity.order.OrderSupplier
import com.example.mshop.domain.entity.order.UpsertOrderPromotion

fun OrderDto.toOrder(): Order {
    return Order(
        orderId = orderId,
        cartId = cartId,
        payment = payment?.toPayment(),
        user = user.toUser(),
        totalPrice = totalPrice,
        totalPromotionPrice = totalPromotionPrice,
        totalShipmentPrice = totalShipmentPrice,
        finalPrice = finalPrice,
        address = address,
        cartProducts = cartProducts
//        orderSuppliers = orderSuppliers.map {
//            it.toOrderSupplier()
//        }
    )
}

fun OrderSupplierDto.toOrderSupplier(): OrderSupplier {
    return OrderSupplier(
        orderSupplierId = orderSupplierId,
        supplier = supplier.toSupplierInfo(),
        shipment = shipment,
        status = status,
        totalPrice = totalPrice,
        expectedReceiveDateTime = expectedReceiveDateTime,
        orderCartProducts = orderCartProducts.map {
            it.toOrderCartProduct()
        }
    )
}

fun OrderCartProductDto.toOrderCartProduct(): OrderCartProduct {
    return OrderCartProduct(
        cartProductId = cartProductId,
        cartId = cartId,
        quantity = quantity,
        totalPrice = totalPrice,
        product = product.toOrderProductInfo()
    )
}

fun OrderProductInfoDto.toOrderProductInfo(): OrderProductInfo {
    return OrderProductInfo(
        productId = productId,
        supplierId = supplierId,
        name = name,
        sku = sku,
        images = images,
        price = price,
        brand = brand,
        weight = weight,
        height = height,
        length = length,
        width = width,
    )
}

fun UpsertOrderPromotionDto.toUpsertOrderPromotion(): UpsertOrderPromotion {
    return UpsertOrderPromotion(
        promotionId, orderSupplierId, totalPrice, totalPromotionPrice, finalPrice
    )
}

fun UpsertOrderPromotion.toUpsertOrderPromotionDto(): UpsertOrderPromotionDto {
    return UpsertOrderPromotionDto(promotionId, orderSupplierId)
}