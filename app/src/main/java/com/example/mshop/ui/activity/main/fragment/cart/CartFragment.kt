package com.example.mshop.ui.activity.main.fragment.cart

import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.utils.DialogUtils
import com.example.mshop.databinding.FragmentCartBinding
import com.example.mshop.ui.activity.main.MainActivity
import com.example.mshop.ui.activity.main.adapter.CartAdapter
import com.example.mshop.ui.ktx.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding, CartViewModel>() {
    override val viewModel: CartViewModel by viewModels()
    override val enableStateScreen: Boolean
        get() = true

    private val adapter: CartAdapter by lazy {
        CartAdapter(
            onUpdateItemAmount = {
                viewModel.updateProduct(it)
            },
            onDeleteItem = { addSupplierProduct ->
                viewModel.deleteItemFromCart(addSupplierProduct)
            }
        )
    }

    override fun initView() {
        binding.viewModel = viewModel
        binding.rcvItems.adapter = adapter
        binding.refresh.setOnRefreshListener {
            viewModel.getActiveCart()
        }
        viewModel.getActiveCart()
        binding.btnBuyNow.setOnClickListener {
            viewModel.buy()
        }
    }

    override fun observer() {
        super.observer()
        viewModel.cart.observe(viewLifecycleOwner) {
            binding.refresh.isRefreshing = false
            adapter.submitList(it.cartProducts)
            setBadgeCart(it.cartProducts.size)
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

    private fun setBadgeCart(amount: Int) {
        (requireActivity() as MainActivity).setBadgeCart(amount)
    }
}