package com.example.mshop.ui.activity.auth.fragment.sign_in

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.sudo248.base_android.base.BaseFragment
import com.example.mshop.R
import com.example.mshop.databinding.FragmentSignInBinding
import com.example.mshop.ui.activity.auth.AuthActivity
import com.example.mshop.ui.activity.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding, SignInViewModel>() {
    override val layoutId: Int = R.layout.fragment_sign_in
    override val viewModel: SignInViewModel by viewModels()
    private val authViewModel: AuthViewModel by activityViewModels()

    companion object {
        fun newInstance(): SignInFragment {
            return SignInFragment()
        }
    }

    override fun initView() {
        binding.viewModel = viewModel
        viewModel.setParentViewModel(authViewModel)
        (activity as AuthActivity).requestViewPagerLayout()
    }
}