package com.example.mshop.data.api.discovery

import com.sudo248.base_android_annotation.apiservice.ApiService
import com.sudo248.base_android_annotation.apiservice.EnableAuthentication
import com.sudo248.base_android_annotation.apiservice.logging_level.Level
import com.sudo248.base_android_annotation.apiservice.logging_level.LoggingLever
import com.example.mshop.BuildConfig
import com.example.mshop.data.api.BaseResponse
import com.example.mshop.data.dto.discovery.CategoryDto
import com.example.mshop.data.dto.discovery.CommentListDto
import com.example.mshop.data.dto.discovery.ProductDto
import com.example.mshop.data.dto.discovery.ProductInfoDto
import com.example.mshop.data.dto.discovery.ProductListDto
import com.example.mshop.data.dto.discovery.ReviewDto
import com.example.mshop.data.dto.discovery.ReviewListDto
import com.example.mshop.data.dto.discovery.SupplierDto
import com.example.mshop.data.dto.discovery.UpsertReviewDto
import com.example.mshop.domain.common.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

@ApiService(baseUrl = com.example.mshop.BuildConfig.BASE_URL + "discovery/")
@EnableAuthentication(Constants.Key.TOKEN)
@LoggingLever(level = Level.BODY)
interface DiscoveryService {
    @GET("categories")
    suspend fun getCategories(
        @Query("location") location: String = Constants.location
    ): Response<com.example.mshop.data.api.BaseResponse<List<CategoryDto>>>

    @GET("categories/{categoryId}")
    suspend fun getCategoryById(
        @Path("categoryId") categoryId: String
    ): Response<com.example.mshop.data.api.BaseResponse<CategoryDto>>

    @GET("categories/{categoryId}/products")
    suspend fun getProductListByCategoryId(
        @Path("categoryId") categoryId: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Constants.DEFAULT_LIMIT,
    ): Response<com.example.mshop.data.api.BaseResponse<ProductListDto>>

    @GET("products")
    suspend fun getProductList(
        @Query("categoryId") categoryId: String,
        @Query("name") name: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Constants.DEFAULT_LIMIT,
    ): Response<com.example.mshop.data.api.BaseResponse<ProductListDto>>

    @GET("products/recommend")
    suspend fun getRecommendProductList(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Constants.DEFAULT_LIMIT,
    ): Response<com.example.mshop.data.api.BaseResponse<ProductListDto>>

    @GET("products/{productId}")
    suspend fun getProductDetail(
        @Path("productId") productId: String
    ): Response<com.example.mshop.data.api.BaseResponse<ProductDto>>

    @GET("products/{productId}/info")
    suspend fun getProductInfo(
        @Path("productId") productId: String
    ): Response<com.example.mshop.data.api.BaseResponse<ProductInfoDto>>

    @GET("products/search/{productName}")
    suspend fun searchProductByName(
        @Path("productName") productName: String
    ): Response<com.example.mshop.data.api.BaseResponse<ProductListDto>>

    @GET("suppliers/{supplierId}")
    suspend fun getSupplierDetail(
        @Path("supplierId") supplierId: String
    ): Response<com.example.mshop.data.api.BaseResponse<SupplierDto>>

    @GET("comments")
    suspend fun getCommentsOfProduct(
        @Query("productId") productId: String,
        @Query("isReviewed") isReviewed: Boolean = true,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Constants.DEFAULT_LIMIT,
    ): Response<com.example.mshop.data.api.BaseResponse<CommentListDto>>

    @GET("reviews")
    suspend fun getReviews(
        @Query("isReviewed") isReviewed: Boolean,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = Constants.DEFAULT_LIMIT,
    ): Response<com.example.mshop.data.api.BaseResponse<ReviewListDto>>

    @POST("reviews")
    suspend fun upsertReview(
        @Body upsertReview: UpsertReviewDto
    ): Response<com.example.mshop.data.api.BaseResponse<ReviewDto>>

    @DELETE("comments/{commentId}")
    suspend fun deleteComment(
        @Path("commentId") commentId: String
    ): Response<com.example.mshop.data.api.BaseResponse<Any>>
}