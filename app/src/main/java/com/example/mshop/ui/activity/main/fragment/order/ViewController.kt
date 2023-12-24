package com.example.mshop.ui.activity.main.fragment.order

interface ViewController {
    fun openVnPaySdk()
    fun payWithCODSuccess()
    fun payWithCODError()
    fun toast(message: String)
}