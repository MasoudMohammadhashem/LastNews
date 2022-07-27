package com.mohammadhashem.lastnews.presentation.ui.adapter.sources

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.mohammadhashem.lastnews.common.utils.imageloader.GlideImageLoader
import com.mohammadhashem.lastnews.data.model.Source
import com.mohammadhashem.lastnews.databinding.ItemSourcesBinding
import com.mohammadhashem.lastnews.presentation.ui.adapter.listerner.OnClickAdapter
import com.mohammadhashem.lastnews.presentation.ui.base.BaseViewHolder

class SourceViewHolder(
    override val binding: ItemSourcesBinding,
    override val context: Context,
    private val onclick: OnClickAdapter
) :
    BaseViewHolder<Source>(binding, context) {
    override fun bind(currentSource: Source, imageLoader: GlideImageLoader, position: Int) {
        binding.tvSourceTitle.text = currentSource.name
        binding.tvSourceDes.text = currentSource.description
        binding.tvLink.text = currentSource.url
        binding.tvSourceCat.text = currentSource.category
        binding.tvSourceLang.text = currentSource.language
        binding.tvSourceCountry.text = currentSource.country
        binding.root.setOnClickListener { onclick.onClickRoot(currentSource.id,currentSource.name) }
        binding.tvLink.setOnClickListener { onclick.onClickLink(currentSource.url) }
    }

}
