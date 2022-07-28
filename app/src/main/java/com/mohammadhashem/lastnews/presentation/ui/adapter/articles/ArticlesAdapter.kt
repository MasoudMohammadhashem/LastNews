package com.mohammadhashem.lastnews.presentation.ui.adapter.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mohammadhashem.lastnews.common.utils.imageloader.GlideImageLoader
import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.databinding.ItemNewsBinding
import com.mohammadhashem.lastnews.presentation.ui.adapter.listerner.OnClickAdapter
import com.mohammadhashem.lastnews.presentation.ui.fragment.secondfragment.NewsFragment

class ArticlesAdapter() : RecyclerView.Adapter<ArticleViewHolder>() {

    lateinit var imageLoader:GlideImageLoader
    lateinit var onClickAdapter:OnClickAdapter
    private var listData:List<Article> = ArrayList<Article>()

    fun setData(listData: List<Article>,
     onClickAdapter: OnClickAdapter,
     imageLoader: GlideImageLoader){
        this.onClickAdapter = onClickAdapter
        this.imageLoader = imageLoader
        this.listData = listData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context,
            onClickAdapter
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(listData[position], imageLoader, position)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}
