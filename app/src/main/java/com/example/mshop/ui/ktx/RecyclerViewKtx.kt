package com.example.mshop.ui.ktx

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView



fun RecyclerView.setHorizontalViewPort(viewport: Float) {
    val ceil = kotlin.math.ceil(viewport)
    val floor = kotlin.math.floor(viewport)
    val countSpace = if (ceil == floor && ceil == viewport) {
        viewport.toInt()
    } else {
        viewport.toInt() - 1
    }
    addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.right = ((width - view.width * viewport) / countSpace).toInt()
        }
    })
}