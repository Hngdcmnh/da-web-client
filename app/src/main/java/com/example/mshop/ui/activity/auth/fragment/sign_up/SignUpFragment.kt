package com.example.mshop.ui.activity.auth.fragment.sign_up

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.example.mshop.R
import com.example.mshop.databinding.FragmentSignUpBinding
import com.example.mshop.ui.activity.auth.AuthActivity
import com.example.mshop.ui.activity.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>() {
    override val layoutId: Int = R.layout.fragment_sign_up
    override val viewModel: SignUpViewModel by viewModels()
    private val authViewModel: AuthViewModel by activityViewModels()

    companion object {
        fun newInstance(): SignUpFragment {
            return SignUpFragment()
        }
    }

    override fun initView() {
        binding.viewModel = viewModel
        viewModel.setParentViewModel(authViewModel)
        viewModel.gotoSignIn = { (activity as AuthActivity).selectedTab(0) }
        (activity as AuthActivity).requestViewPagerLayout()
    }
}