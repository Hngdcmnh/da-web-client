package com.example.mshop.ui.uimodel

import com.example.mshop.R

enum class StepChooseAddress(val value: Int) {
    PROVINCE(R.string.choose_province) {
        override fun nextStep(): StepChooseAddress = DISTRICT
    },
    DISTRICT(R.string.choose_district) {
        override fun nextStep(): StepChooseAddress = WARD
    },
    WARD(R.string.choose_ward) {
        override fun nextStep(): StepChooseAddress = CLOSE
    },
    CLOSE(-1) {
        override fun nextStep(): StepChooseAddress = PROVINCE
    };

    abstract fun nextStep(): StepChooseAddress
}