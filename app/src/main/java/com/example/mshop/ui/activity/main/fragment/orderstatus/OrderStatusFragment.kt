package com.example.mshop.ui.activity.main.fragment.orderstatus

import android.view.View
import androidx.fragment.app.viewModels
import com.example.mshop.databinding.FragmentOrderStatusBinding
import com.google.android.material.tabs.TabLayout
import com.sudo248.base_android.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrderStatusFragment : BaseFragment<FragmentOrderStatusBinding, OrderStatusViewModel>() {

    override val viewModel: OrderStatusViewModel by viewModels()
    override fun initView() {
        binding.viewModel = viewModel
        binding.rcvOrderStatusPrepare.setHasFixedSize(true)
        binding.rcvOrderStatusPrepare.adapter = viewModel.orderStatusAdapter

        binding.rcvOrderStatusShipping.setHasFixedSize(true)
        binding.rcvOrderStatusShipping.adapter = viewModel.orderStatusAdapter

        binding.rcvOrderStatusReceived.setHasFixedSize(true)
        binding.rcvOrderStatusReceived.adapter = viewModel.orderStatusAdapter

        setupTabLayout()

        binding.refresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun setupTabLayout() {
        binding.tabOrderStatus.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    when (it.position) {
                        0 -> {
                            setPrepareItem(com.example.mshop.ui.models.order.OrderStatusTab.PREPARE)
                            viewModel.onClickTabItem(com.example.mshop.ui.models.order.OrderStatusTab.PREPARE)
                        }
                        1 -> {
                            setPrepareItem(com.example.mshop.ui.models.order.OrderStatusTab.SHIPPING)
                            viewModel.onClickTabItem(com.example.mshop.ui.models.order.OrderStatusTab.SHIPPING)
                        }
                        else -> {
                            setPrepareItem(com.example.mshop.ui.models.order.OrderStatusTab.RECEIVED)
                            viewModel.onClickTabItem(com.example.mshop.ui.models.order.OrderStatusTab.RECEIVED)
                        }
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun observer() {
        viewModel.isRefresh.observe(viewLifecycleOwner) {
            binding.refresh.isRefreshing = it
        }
    }

    private fun setPrepareItem(orderStatusTab: com.example.mshop.ui.models.order.OrderStatusTab) {
        when(orderStatusTab){
            com.example.mshop.ui.models.order.OrderStatusTab.PREPARE -> {
                binding.rcvOrderStatusPrepare.visibility = View.VISIBLE
                binding.rcvOrderStatusShipping.visibility = View.GONE
                binding.rcvOrderStatusReceived.visibility = View.GONE
            }
            com.example.mshop.ui.models.order.OrderStatusTab.SHIPPING -> {
                binding.rcvOrderStatusPrepare.visibility = View.GONE
                binding.rcvOrderStatusShipping.visibility = View.VISIBLE
                binding.rcvOrderStatusReceived.visibility = View.GONE
            }
            else ->{
                binding.rcvOrderStatusPrepare.visibility = View.GONE
                binding.rcvOrderStatusShipping.visibility = View.GONE
                binding.rcvOrderStatusReceived.visibility = View.VISIBLE
            }
        }
    }
}