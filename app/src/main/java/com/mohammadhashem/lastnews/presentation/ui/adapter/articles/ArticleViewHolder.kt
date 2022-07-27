package com.mohammadhashem.lastnews.presentation.ui.adapter.articles

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.mohammadhashem.lastnews.common.utils.imageloader.GlideImageLoader
import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.data.model.Source
import com.mohammadhashem.lastnews.databinding.ItemNewsBinding
import com.mohammadhashem.lastnews.databinding.ItemSourcesBinding
import com.mohammadhashem.lastnews.presentation.ui.adapter.listerner.OnClickAdapter
import com.mohammadhashem.lastnews.presentation.ui.base.BaseViewHolder

class ArticleViewHolder(
    override val binding: ItemNewsBinding,
    override val context: Context,
    private val onclick: OnClickAdapter
) :
    BaseViewHolder<Article>(binding, context) {
    override fun bind(currentArticle: Article, imageLoader: GlideImageLoader, position: Int) {
        binding.tvAuthor.text = currentArticle.author
        var time = currentArticle.publishedAt.replace("T"," ")
        time = time.replace("Z","")
        binding.tvDate.text = time
        binding.tvDesc.text = currentArticle.description
        binding.tvTitle.text = currentArticle.title
        binding.tvSource.text = currentArticle.source.name
        imageLoader.loadImage(binding.img,currentArticle.urlToImage)
        binding.root.setOnClickListener {
            onclick.onClickLink(currentArticle.url)
        }
    }

}
