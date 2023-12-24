package com.example.mshop.ui.activity.payment

import androidx.activity.viewModels
import com.sudo248.base_android.base.BaseActivity
import com.example.mshop.databinding.ActivityPaymentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentActivity : BaseActivity<ActivityPaymentBinding, PaymentViewModel>() {
    override val viewModel: PaymentViewModel by viewModels()

    override fun initView() {

    }
}