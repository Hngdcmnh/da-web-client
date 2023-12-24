package com.example.mshop.ui.activity.main.fragment.user

import android.net.Uri
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.sudo248.base_android.base.BaseFragment
import com.example.mshop.R
import com.example.mshop.databinding.FragmentEditUserBinding
import com.example.mshop.domain.entity.user.Gender
import com.example.mshop.ui.activity.main.MainViewModel
import com.example.mshop.ui.uimodel.adapter.loadImage
import com.example.mshop.ui.util.FileUtils
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class UserEditFragment : BaseFragment<FragmentEditUserBinding, UserViewModel>(), ViewController {
    override val viewModel: UserViewModel by activityViewModels()
    private val mainViewModel: MainViewModel by activityViewModels()
    override val enableStateScreen: Boolean = true

    override fun initView() {
        binding.viewModel = viewModel
        viewModel.setActivityViewModel(mainViewModel)
        viewModel.setViewController(this)
        initGender()
    }

    private fun initGender() {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_textview, Gender.values().map { it.value })
        val autoCompleteTextView = binding.edtGender.editText as? AutoCompleteTextView
        autoCompleteTextView?.setText(viewModel.user.value?.gender?.get())
        autoCompleteTextView?.setAdapter(adapter)
        autoCompleteTextView?.setOnItemClickListener { _, _, index, _ ->
            viewModel.user.value?.gender?.set(adapter.getItem(index))
        }
    }

    override fun showDialogDatePicker(now: Date?){
        val selectDate = now?.time ?: MaterialDatePicker.todayInUtcMilliseconds()
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText("Your Birthday")
                .setSelection(selectDate)
                .build()

        activity?.supportFragmentManager?.let { datePicker.show(it, "Show date picker") }

        datePicker.addOnPositiveButtonClickListener {
            viewModel.selectDate(datePicker.selection)
        }
    }

    override fun showBottomSheetChooseAddress() {
        val bottomSheetFragment = ChooseAddressBottomSheetFragment.newInstance(viewModel)
        bottomSheetFragment.show(childFragmentManager, ChooseAddressBottomSheetFragment.TAG)
        bottomSheetFragment.dialog?.setOnDismissListener {
            binding.edtAddress.editText?.requestFocus()
        }
    }

    override fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun getPathImageFromUri(uri: Uri): String {
        return FileUtils.getPathFromUri(requireContext(), uri)
    }

    override fun pickImage() {
        mainViewModel.pickImage()
    }

    override fun observer() {
        super.observer()
        mainViewModel.imageUri.observe(viewLifecycleOwner) {
            it?.let {
                loadImage(binding.imgUserAvatar, it)
            }
        }
    }

}