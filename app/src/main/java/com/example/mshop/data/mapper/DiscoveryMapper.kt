package com.example.mshop.data.mapper

import com.example.mshop.data.dto.discovery.CategoryDto
import com.example.mshop.data.dto.discovery.CategoryInfoDto
import com.example.mshop.data.dto.discovery.CommentDto
import com.example.mshop.data.dto.discovery.CommentListDto
import com.example.mshop.data.dto.discovery.LocationDto
import com.example.mshop.data.dto.discovery.PaginationDto
import com.example.mshop.data.dto.discovery.ProductDto
import com.example.mshop.data.dto.discovery.ProductExtrasDto
import com.example.mshop.data.dto.discovery.ProductInfoDto
import com.example.mshop.data.dto.discovery.ProductListDto
import com.example.mshop.data.dto.discovery.ReviewDto
import com.example.mshop.data.dto.discovery.ReviewListDto
import com.example.mshop.data.dto.discovery.SupplierDto
import com.example.mshop.data.dto.discovery.SupplierInfoDto
import com.example.mshop.data.dto.discovery.UpsertReviewDto
import com.example.mshop.data.dto.discovery.UserInfoDto
import com.example.mshop.domain.entity.discovery.Category
import com.example.mshop.domain.entity.discovery.CategoryInfo
import com.example.mshop.domain.entity.discovery.Comment
import com.example.mshop.domain.entity.discovery.CommentList
import com.example.mshop.domain.entity.discovery.Location
import com.example.mshop.domain.entity.discovery.Pagination
import com.example.mshop.domain.entity.discovery.Product
import com.example.mshop.domain.entity.discovery.ProductExtras
import com.example.mshop.domain.entity.discovery.ProductInfo
import com.example.mshop.domain.entity.discovery.ProductList
import com.example.mshop.domain.entity.discovery.Review
import com.example.mshop.domain.entity.discovery.ReviewList
import com.example.mshop.domain.entity.discovery.Supplier
import com.example.mshop.domain.entity.discovery.SupplierInfo
import com.example.mshop.domain.entity.discovery.UpsertReview
import com.example.mshop.domain.entity.discovery.UserInfo

fun ProductDto.toProduct(): Product {
    return Product(
        productId = productId,
        name = name,
        supplierId = supplierId,

        description = description,
        brand = brand,
        sku = sku,
        price = price,
        listedPrice = listedPrice,
        amount = amount,
        soldAmount = soldAmount,
        color = color,
        size = size,
        rate = rate,
        discount = discount,
        startDateDiscount = startDateDiscount,
        endDateDiscount = endDateDiscount,
        saleable = saleable,
        weight = weight,
        height = height,
        length = length,
        width = width,
        extras = extras.toProductExtras(),
        images = images.map { it.url },
        supplier = supplier?.toSupplierInfo(),
        categories = categories?.map { it.toCategoryInfo() },
    )
}

fun ProductInfoDto.toProductInfo(): ProductInfo {
    return ProductInfo(
        productId = productId,
        name = name,
        sku = sku,
        price = price,
        listedPrice = listedPrice,
        amount = amount,
        soldAmount = soldAmount,
        color = color,
        size = size,
        rate = rate,
        discount = discount,
        startDateDiscount = startDateDiscount,
        endDateDiscount = endDateDiscount,
        saleable = saleable,
        images = images,
        brand = brand,
    )
}

fun ProductListDto.toProductList(): ProductList {
    return ProductList(
        products = products.map { it.toProductInfo() },
        pagination = pagination.toPagination()
    )
}

fun CategoryDto.toCategory(): Category {
    return Category(
        categoryId = categoryId,
        name = name,
        image = image,
    )
}

fun CategoryInfoDto.toCategoryInfo(): CategoryInfo {
    return CategoryInfo(
        categoryId, name, image
    )
}

fun SupplierDto.toSupplier(): Supplier {
    return Supplier(
        supplierId = supplierId,
        name = name,
        avatar = avatar,
        brand = brand,
        contactUrl = contactUrl,
        totalProducts = totalProducts,
        rate = rate,
        createAt = createAt,
        address = address.toAddress()
    )
}

fun SupplierInfoDto.toSupplierInfo(): SupplierInfo {
    return SupplierInfo(
        supplierId = supplierId,
        name = name,
        avatar = avatar,
        contactUrl = contactUrl,
        rate = rate,
        address = address.toAddress()
    )
}

fun LocationDto.toLocation(): Location {
    return Location(longitude, latitude)
}

fun CommentDto.toComment(): Comment {
    return Comment(
        commentId = commentId,
        productId = productId,
        rate = rate,
        isReviewed = isReviewed,
        comment = comment,
        updatedAt = updatedAt,
        createdAt = createdAt,
        userInfo = userInfo.toUserInfo(),
        images = images,
    )
}

fun UserInfoDto.toUserInfo(): UserInfo {
    return UserInfo(
        userId,
        fullName,
        avatar,
    )
}

fun CommentListDto.toCommentList(): CommentList {
    return CommentList(
        comments = comments.map { it.toComment() },
        pagination = pagination.toPagination(),
    )
}

fun PaginationDto.toPagination(): Pagination {
    return Pagination(
        offset = offset,
        total = total
    )
}

fun ReviewListDto.toReviewList(): ReviewList {
    return ReviewList(
        reviews = reviews.map { it.toReview() },
        pagination = pagination.toPagination(),
    )
}

fun UpsertReview.toUpsertCommentDto(): UpsertReviewDto {
    return UpsertReviewDto(
        reviewId = reviewId,
        rate = rate,
        comment = comment,
        images = images
    )
}

fun ReviewDto.toReview(): Review {
    return Review(
        reviewId = reviewId,
        rate = rate,
        isReviewed = isReviewed,
        comment = comment,
        updatedAt = updatedAt,
        createdAt = createdAt,
        userInfo = userInfo.toUserInfo(),
        productInfo = productInfo?.toProductInfo()
    )
}

fun ProductExtrasDto.toProductExtras(): ProductExtras {
    return ProductExtras(
        enable3DViewer = enable3DViewer,
        enableArViewer = enableArViewer,
        source = source
    )
}