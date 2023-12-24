package com.example.mshop.ui.activity.main.fragment.user

import androidx.fragment.app.activityViewModels
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.utils.DialogUtils
import com.example.mshop.databinding.FragmentUserBinding
import com.example.mshop.ui.activity.main.MainViewModel
import com.example.mshop.ui.ktx.showErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : BaseFragment<FragmentUserBinding, UserViewModel>() {
    override val viewModel: UserViewModel by activityViewModels()
    private val mainActivityViewModel: MainViewModel by activityViewModels()
    override val enableStateScreen: Boolean = true

    override fun initView() {
        binding.viewModel = viewModel
        binding.rcvOrder.setHasFixedSize(true)

        binding.rcvOrder.adapter = viewModel.buyedOrderAdapter
        viewModel.setActivityViewModel(mainActivityViewModel)
    }

    override fun onResume() {
        super.onResume()
        binding.viewModel?.getOrderInfo()
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
}