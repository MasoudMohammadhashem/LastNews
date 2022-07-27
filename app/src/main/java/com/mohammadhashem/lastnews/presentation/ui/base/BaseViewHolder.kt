package com.mohammadhashem.lastnews.presentation.ui.base

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.mohammadhashem.lastnews.common.utils.imageloader.GlideImageLoader

abstract class BaseViewHolder<T>(open val  binding: ViewBinding,open val context: Context) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(task: T, imageLoader: GlideImageLoader, position: Int)
}
