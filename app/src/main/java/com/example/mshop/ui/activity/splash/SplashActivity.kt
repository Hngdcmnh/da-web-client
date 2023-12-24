package com.example.mshop.ui.activity.splash

import android.annotation.SuppressLint
import androidx.activity.viewModels
import com.sudo248.base_android.base.BaseActivity
import com.example.mshop.R
import com.example.mshop.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(){
    override val layoutId: Int = R.layout.activity_splash
    override val viewModel: SplashViewModel by viewModels()

    override fun initView() {
    }

}