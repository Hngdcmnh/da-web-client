package com.example.mshop.ui.activity.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sudo248.base_android.base.BaseListAdapter
import com.sudo248.base_android.base.BaseViewHolder
import com.example.mshop.databinding.ItemCategoryBinding
import com.example.mshop.domain.entity.discovery.Category
import com.example.mshop.ui.uimodel.adapter.loadImage


class CategoryAdapter(
    private val onItemClick: (Category) -> Unit,
) : BaseListAdapter<Category, CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class ViewHolder(binding: ItemCategoryBinding) :
        BaseViewHolder<Category, ItemCategoryBinding>(binding) {

        override fun onBind(item: Category) {
            loadImage(binding.imgCategory, item.image)
            binding.txtNameCategory.text = item.name

            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }
}