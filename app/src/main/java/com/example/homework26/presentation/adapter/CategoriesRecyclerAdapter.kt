package com.example.homework26.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework26.databinding.ItemCategoriesListLayoutBinding
import com.example.homework26.presentation.model.Categories

class CategoriesRecyclerAdapter :
    ListAdapter<Categories, CategoriesRecyclerAdapter.CategoryViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoriesListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class CategoryViewHolder(private val binding: ItemCategoriesListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Categories) {
            binding.tvItemName.text = category.name

            val childrenAdapter = CategoriesChildrenRecyclerAdapter(category.numberOfChildren)
            binding.rvChildren.apply {
                adapter = childrenAdapter
                layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Categories>() {
            override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean {
                return oldItem == newItem
            }
        }
    }
}