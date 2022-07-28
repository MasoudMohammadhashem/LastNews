package com.mohammadhashem.lastnews.presentation.ui.adapter.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.mohammadhashem.lastnews.common.utils.imageloader.GlideImageLoader
import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.databinding.ItemNewsBinding
import com.mohammadhashem.lastnews.presentation.ui.adapter.listerner.OnClickAdapter


class ArticlePagingAdapter : PagingDataAdapter<Article, ArticleViewHolder>(COMPARATOR) {

    lateinit var imageLoader: GlideImageLoader
    lateinit var onClickAdapter: OnClickAdapter
    fun setData(onClickAdapter: OnClickAdapter,
                imageLoader: GlideImageLoader){
        this.onClickAdapter = onClickAdapter
        this.imageLoader = imageLoader

//        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context,
            onClickAdapter
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, imageLoader, position)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url || oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }
}