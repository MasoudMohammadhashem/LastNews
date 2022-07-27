package com.mohammadhashem.lastnews.presentation.ui.adapter.sources

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohammadhashem.lastnews.common.utils.imageloader.GlideImageLoader
import com.mohammadhashem.lastnews.data.model.Source
import com.mohammadhashem.lastnews.databinding.ItemSourcesBinding
import com.mohammadhashem.lastnews.presentation.ui.adapter.listerner.OnClickAdapter
import javax.inject.Inject

class SourcesAdapter : RecyclerView.Adapter<SourceViewHolder>() {


    private lateinit var imageLoader : GlideImageLoader
    private lateinit var onClickAdapter: OnClickAdapter
    private var listData: List<Source> = ArrayList<Source>()

    fun setData(listData: List<Source>,onClickAdapter:OnClickAdapter,imageLoader : GlideImageLoader) {
        this.listData = listData
        this.onClickAdapter = onClickAdapter
        this.imageLoader = imageLoader
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        return SourceViewHolder(
            ItemSourcesBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context,
            onClickAdapter
        )
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        holder.bind(listData[position], imageLoader, position)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}
