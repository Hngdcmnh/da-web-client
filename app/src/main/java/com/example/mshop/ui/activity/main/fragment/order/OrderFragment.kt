package com.example.mshop.ui.activity.main.fragment.order

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.ktx.showWithTransparentBackground
import com.sudo248.base_android.utils.DialogUtils
import com.example.mshop.R
import com.example.mshop.databinding.DialogChoosePaymentMethodBinding
import com.example.mshop.databinding.FragmentOrderBinding
import com.example.mshop.domain.common.Constants
import com.example.mshop.domain.entity.order.Order
import com.example.mshop.ui.activity.main.MainActivity
import com.example.mshop.ui.activity.main.adapter.OrderProductAdapter
import com.example.mshop.ui.ktx.showErrorDialog
import com.example.mshop.ui.util.Utils
import com.vnpay.authentication.VNP_AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OrderFragment : BaseFragment<FragmentOrderBinding, OrderViewModel>(), ViewController {
    override val viewModel: OrderViewModel by viewModels()
    private val args: OrderFragmentArgs by navArgs()

    override val enableStateScreen: Boolean = true

    companion object {
        var actionVnPaySdk: String? = null
    }

    private var adapter: OrderProductAdapter = OrderProductAdapter(orderProducts = listOf())

    override fun initView() {
        binding.viewModel = viewModel
        viewModel.viewController = this
        binding.rcvOrderSupplier.adapter = adapter
        viewModel.createOrder(args.cartId)
    }

    override fun onResume() {
        super.onResume()
        actionVnPaySdk?.let {
            processActionFromVnPaySdk(it)
        }
    }

    override fun observer() {
        viewModel.order.observe(viewLifecycleOwner) {
            setupOrder(order = it)
        }

        viewModel.finalPrice.observe(viewLifecycleOwner) {
            binding.apply {
                val finalPrice = Utils.formatVnCurrency(it)
                txtFinalPrice.text = finalPrice
                txtPaymentAmount.text = finalPrice
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupOrder(order: Order) {
        adapter.orderProducts = order.cartProducts
        adapter.notifyDataSetChanged()

        binding.apply {
            txtTotalPrice.text = Utils.formatVnCurrency(order.totalPrice)
            txtTotalShipmentPrice.text = Utils.formatVnCurrency(order.totalShipmentPrice)
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

    private fun showDialogChoosePaymentType() {
        val dialog = Dialog(requireContext())
        val dialogBinding: DialogChoosePaymentMethodBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.dialog_choose_payment_method,
            binding.root as ViewGroup,
            false
        )
        dialog.setContentView(dialogBinding.root)
        dialogBinding.lifecycleOwner = this
        dialogBinding.viewModel = viewModel
        dialogBinding.txtAgree.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setCancelable(false)
        dialog.showWithTransparentBackground()
    }

    override fun openVnPaySdk() {
        lifecycleScope.launch {
            val vnPayUrl = viewModel.getVnPayPaymentUrl().await()
            vnPayUrl?.let {
                val intentVnPaySdk = getIntentVnPay(it)
                setupSdkCompletedCallback()
                startActivity(intentVnPaySdk)
            }
        }
    }

    override fun payWithCODSuccess() {

        navigateOff(OrderFragmentDirections.actionOrderFragmentToOrderStatusFragment2())
    }

    override fun payWithCODError() {
        navigateOff(OrderFragmentDirections.actionOrderFragmentToOrderStatusFragment2())
    }

    override fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun getIntentVnPay(vnPayUrl: String): Intent {
        return Intent(requireActivity(), VNP_AuthenticationActivity::class.java).apply {
            putExtra(Constants.Payment.KEY_URL, vnPayUrl)
            putExtra(Constants.Payment.KEY_TMN_CODE, Constants.Payment.TMN_CODE)
            putExtra(Constants.Payment.KEY_SCHEME, Constants.Payment.SCHEME)
            putExtra(Constants.Payment.KEY_IS_SANDBOX, Constants.Payment.IS_SANDBOX)
        }
    }

    private fun setupSdkCompletedCallback() {
        VNP_AuthenticationActivity.setSdkCompletedCallback {
            Log.d("Sudoo", "VNPaySdkCompletedCallback: $it")
            actionVnPaySdk = it
        }
    }

    private fun processActionFromVnPaySdk(action: String) {
        Log.d("Sudoo", "processActionFromVnPaySdk: $action")
        when (action) {
            Constants.Payment.ACTION_APP_BACK -> {
                onVnPaySdkActionAppBack()
            }

            Constants.Payment.ACTION_CALL_MOBILE_BANKING_APP -> {
                onVnPaySdkActionCallMobileBankingApp()
            }

            Constants.Payment.ACTION_WEB_BACK -> {
                onVnPaySdkActionWebBack()
            }

            Constants.Payment.ACTION_FAILED -> {
                onVnPaySdkActionFailed()
            }

            Constants.Payment.ACTION_SUCCESS -> {
                onVnPaySdkActionSuccess()
            }

            else -> {
                toast(action)
            }
        }
        actionVnPaySdk = null
    }

    private fun onVnPaySdkActionAppBack() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.putExtra(Constants.Key.SCREEN, Constants.Screen.DISCOVERY)
        startActivity(intent)
    }

    private fun onVnPaySdkActionCallMobileBankingApp() {

    }

    private fun onVnPaySdkActionWebBack() {

    }

    private fun onVnPaySdkActionFailed() {
        toast(R.string.payment_fail)
        //
    }

    private fun onVnPaySdkActionSuccess() {
        toast(R.string.payment_success)
        navigateOff(OrderFragmentDirections.actionOrderFragmentToReviewListFragment(isAfterPayment = true))
    }

    private fun toast(@StringRes id: Int) {
        Toast.makeText(requireContext(), getString(id), Toast.LENGTH_SHORT).show()
    }
}