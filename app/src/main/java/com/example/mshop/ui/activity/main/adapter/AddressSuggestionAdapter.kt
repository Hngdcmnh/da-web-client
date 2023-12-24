package com.example.mshop.ui.activity.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mshop.R
import com.example.mshop.domain.entity.user.AddressSuggestion

class AddressSuggestionAdapter(
    private val onItemClick: (AddressSuggestion) -> Unit
) : RecyclerView.Adapter<AddressSuggestionAdapter.ViewHolder>() {

    private val address = mutableListOf<AddressSuggestion>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(address: List<AddressSuggestion>) {
        this.address.clear()
        this.address.addAll(address)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_textview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(address[position])
    }

    override fun getItemCount(): Int = address.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun onBind(address: AddressSuggestion) {
            (itemView as TextView).text = address.addressName
            itemView.setOnClickListener {
                onItemClick.invoke(address)
            }
        }
    }
}