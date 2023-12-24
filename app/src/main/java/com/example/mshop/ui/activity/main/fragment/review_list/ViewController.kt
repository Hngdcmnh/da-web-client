package com.example.mshop.ui.activity.main.fragment.review_list

import com.example.mshop.ui.models.review.ReviewListTab

interface ViewController {
    fun onSelectedTab(tab: ReviewListTab)
}