package com.example.mshop.ui.activity.main.fragment.search

import android.content.Intent
import android.net.Uri
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.ktx.gone
import com.sudo248.base_android.ktx.visible
import com.sudo248.base_android.utils.KeyBoardUtils
import com.example.mshop.R
import com.example.mshop.databinding.FragmentSearchBinding
import com.example.mshop.ui.activity.main.MainActivity
import com.example.mshop.ui.activity.main.fragment.product_detail.ViewController
import com.example.mshop.ui.ktx.requestFocusAndKeyBoard
import com.google.android.material.badge.BadgeDrawable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>(), ViewController {
    override val viewModel: SearchViewModel by viewModels()
    override val enableStateScreen: Boolean = true
    private val args: SearchFragmentArgs by navArgs()


    override fun initView() {
        viewModel.viewController = this
        viewModel.setCategoryId(args.categoryId)
        binding.rcvProduct.adapter = viewModel.productInfoAdapter
        binding.imgBack.setOnClickListener { back() }
        binding.refresh.setOnRefreshListener { viewModel.refresh() }
        setupSearch()
    }

    private fun setupSearch() {
        if (args.categoryId == null) {
            binding.tvSearch.requestFocusAndKeyBoard()
        }

        binding.tvSearch.hint =
            if (args.categoryName == null) getString(R.string.search) else getString(
                R.string.search_in,
                args.categoryName
            )
        binding.tvSearch.addTextChangedListener(onTextChanged = { text, _, _, _ ->
            viewModel.search(name = text.toString())
        })

        binding.tvSearch.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.actionSearch(name = binding.tvSearch.text.toString())
                KeyBoardUtils.hide(requireActivity())
                true
            } else {
                false
            }
        }
    }

    override fun observer() {
        viewModel.isEmptyProduct.observe(viewLifecycleOwner) {
            if (it) {
                binding.apply {
                    imgNotFound.visible()
                    rcvProduct.gone()
                }
            } else {
                binding.rcvProduct.visible()
                binding.imgNotFound.gone()
            }
        }

        viewModel.isRefresh.observe(viewLifecycleOwner) {
            binding.refresh.isRefreshing = it
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