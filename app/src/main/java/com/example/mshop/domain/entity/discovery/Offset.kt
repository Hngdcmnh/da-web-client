package com.example.mshop.domain.entity.discovery

import com.example.mshop.domain.common.Constants

data class Offset(
    var offset: Int = 0,
    var limit: Int = Constants.DEFAULT_LIMIT
) {
    fun reset() {
        offset = 0
        limit = Constants.DEFAULT_LIMIT
    }
}
