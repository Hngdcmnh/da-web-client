package com.example.mshop.ui.activity.main.fragment.discovery

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.utils.DialogUtils
import com.example.mshop.databinding.FragmentDiscoveryBinding
import com.example.mshop.ui.activity.main.DeepLinkHandler
import com.example.mshop.ui.activity.main.MainActivity
import com.example.mshop.ui.activity.main.fragment.product_detail.ViewController
import com.example.mshop.ui.ktx.showErrorDialog
import com.google.android.material.badge.BadgeDrawable
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class DiscoveryFragment : BaseFragment<FragmentDiscoveryBinding, DiscoveryViewModel>(),
    ViewController {
    override val viewModel: DiscoveryViewModel by viewModels()
    override val enableStateScreen: Boolean = true
    private var isHandlerDeeplink = false
//    private var badge: BadgeDrawable? = null

    override fun initView() {

        viewModel.viewController = this
        binding.viewModel = viewModel

        binding.rcvCategories.setHasFixedSize(true)
        binding.rcvCategories.adapter = viewModel.categoryAdapter

        binding.rcvProducts.setHasFixedSize(true)
        binding.rcvProducts.adapter = viewModel.productInfoAdapter

        binding.parentScrollView.setOnScrollChangeListener(viewModel.endlessNestedScrollListener)

        binding.header.search.setOnClickListener {
            viewModel.navigateToSearchView()
        }

        binding.refresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun observer() {
        viewModel.isRefresh.observe(viewLifecycleOwner) {
            binding.refresh.isRefreshing = it
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

    private fun handleDeeplink() {
        if (!isHandlerDeeplink) {
            (activity as MainActivity).setDeepLinkHandler(object : DeepLinkHandler {
                override fun onHandle(link: String) {
                    isHandlerDeeplink = true
                    val productId = link.substringAfterLast("/").substringBefore("?")
                    Log.d("Sudoo", "handleDeeplink: productId: $productId")
                    viewModel.navigateToProductDetail(productId)
                }
            })
            activity?.intent?.data?.path?.let {
                isHandlerDeeplink = true
                val productId = it.substringAfterLast("/").substringBefore("?")
                Log.d("Sudoo", "handleDeeplink: productId: $productId")
                viewModel.navigateToProductDetail(productId)
            }
        }
    }

    override fun setBadgeCart(amount: Int) {
//        (requireActivity() as MainActivity).setBadgeCart(amount)
//        if (amount > 0) {
//            badge?.isVisible = true
//            badge?.number = amount
//        } else {
//            badge?.isVisible = false
//        }
    }

    override fun openContact(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun toast(id: Int, duration: Int) {
        Toast.makeText(requireContext(), getString(id), duration).show()
    }
}