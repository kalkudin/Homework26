package com.example.homework26.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework26.databinding.ItemCategoryChildLayoutBinding

class CategoriesChildrenRecyclerAdapter(private val numberOfChildren: Int) :
    RecyclerView.Adapter<CategoriesChildrenRecyclerAdapter.ChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val binding = ItemCategoryChildLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return minOf(numberOfChildren, 4)
    }

    inner class ChildViewHolder(binding: ItemCategoryChildLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}