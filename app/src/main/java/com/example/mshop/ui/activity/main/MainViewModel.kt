package com.example.mshop.ui.activity.main

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sudo248.base_android.base.BaseViewModel
import com.sudo248.base_android.ktx.onError
import com.sudo248.base_android.ktx.onSuccess
import com.sudo248.base_android.navigation.IntentDirections
import com.example.mshop.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 09:15 - 23/02/2023
 */


@HiltViewModel
class MainViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : BaseViewModel<IntentDirections>() {

    init {
        getItemInCart()
    }

    private val _imageUri = MutableLiveData<Uri?>()
    val imageUri: LiveData<Uri?> = _imageUri

    private var tmpImageUri: Uri? = null

    private val _countCartItem = MutableLiveData(0)
    val countCartItem: LiveData<Int> = _countCartItem

    var viewController: ViewController? = null

    fun setImageUri(uri: Uri?) {
        _imageUri.postValue(uri)
    }

    fun pickImage() {
        viewController?.pickImage()
    }

    fun takeImage() {
        viewController?.createTempPictureUri()?.let {
            tmpImageUri = it
            viewController?.takeImage(it)
        } ?: throw NullPointerException("Can't create temp picture uri")
    }

    fun getTakeImageUri(): Uri? {
        _imageUri.value = tmpImageUri
        return tmpImageUri
    }





    fun getItemInCart() = launch {
        cartRepository.countItemInActiveCart()
            .onSuccess {
                _countCartItem.postValue(it)
            }.onError {
                _countCartItem.postValue(0)
            }
    }

    override fun onCleared() {
        super.onCleared()
        viewController = null
    }

}