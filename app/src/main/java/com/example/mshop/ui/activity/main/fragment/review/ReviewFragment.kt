package com.example.mshop.ui.activity.main.fragment.review

import android.net.Uri
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.utils.DialogUtils
import com.example.mshop.R
import com.example.mshop.databinding.FragmentReviewBinding
import com.example.mshop.domain.entity.discovery.ProductInfo
import com.example.mshop.domain.entity.discovery.UpsertReview
import com.example.mshop.ui.ktx.showErrorDialog
import com.example.mshop.ui.models.comment.RatingDescription
import com.example.mshop.ui.uimodel.adapter.loadImage
import com.example.mshop.ui.util.FileUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewFragment : BaseFragment<FragmentReviewBinding, ReviewViewModel>(), ViewController {
    override val viewModel: ReviewViewModel by viewModels()
    private val args: ReviewFragmentArgs by navArgs()
    override fun initView() {
        viewModel.setViewController(this)
        binding.viewModel  = viewModel
        args.review.productInfo?.let { setProduct(it) }
        setupRating()
    }

    private fun setupRating() {
        binding.rating.setOnRatingBarChangeListener { _, value, b ->
            val ratingDescription = RatingDescription.from(value)
            binding.txtRatingDescription.apply {
                setText(ratingDescription.description)
                setTextColor(ContextCompat.getColor(requireContext(), ratingDescription.color))
            }
        }
    }

    private fun setProduct(productInfo: ProductInfo) {
        binding.apply {
            loadImage(imgProduct, productInfo.images.first())
            txtNameProduct.text = productInfo.name
            txtProductBrand.text = getString(R.string.product_brand, productInfo.brand)
        }
    }

    override fun onStateError() {
        super.onStateError()
        viewModel.error.run {
            val message = getValueIfNotHandled()
            if (!message.isNullOrEmpty()) {
                DialogUtils.showErrorDialog(
                    context = requireContext(),
                    message = message
                )
            }
        }
    }

    override fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun getPathImageFromUri(uri: Uri): String {
        return FileUtils.getPathFromUri(requireContext(), uri)
    }

    override fun getUpsertReview(): UpsertReview {
        return UpsertReview(
            reviewId = args.review.reviewId,
            rate = binding.rating.rating,
            comment = binding.txtComment.text.toString()
        )
    }
}