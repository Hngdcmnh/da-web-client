package com.example.mshop.ui.activity.payment.fragment

interface ViewController {
    fun openVnPaySdk()
    fun payWithCODSuccess()
    fun toast(message: String)
}