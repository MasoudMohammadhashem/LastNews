package com.mohammadhashem.lastnews.presentation.ui.fragment.secondfragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohammadhashem.lastnews.common.utils.imageloader.GlideImageLoader
import com.mohammadhashem.lastnews.data.model.SourceX
import com.mohammadhashem.lastnews.databinding.FragmentNewsBinding
import com.mohammadhashem.lastnews.presentation.ui.adapter.articles.ArticlesAdapter
import com.mohammadhashem.lastnews.presentation.ui.adapter.listerner.OnClickAdapter
import com.mohammadhashem.lastnews.presentation.ui.adapter.sources.SourcesAdapter
import com.mohammadhashem.lastnews.presentation.ui.base.BaseFragment
import com.mohammadhashem.lastnews.presentation.ui.fragment.secondfragment.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>(),OnClickAdapter {

    private val viewModel: NewsViewModel by viewModels()
    @Inject
    lateinit var imageLoader:GlideImageLoader

    override fun setBinding(): FragmentNewsBinding = FragmentNewsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservables()
        gettingExtras()
    }

    private fun setObservables() {
        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.rvArticles.visibility = View.GONE
                binding.progressCircular.visibility = View.VISIBLE
            } else {
                binding.rvArticles.visibility = View.VISIBLE
                binding.progressCircular.visibility = View.GONE
            }
        })
        viewModel.articleList.observe(viewLifecycleOwner, Observer {
            val adapter = ArticlesAdapter()
            binding.rvArticles.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.rvArticles.adapter = adapter
            adapter.setData(it,this,imageLoader)
        })
    }

    private fun gettingExtras(){
        val data = arguments?.getParcelable<SourceX>("sourceX")
        if (data==null){
            Toast.makeText(requireContext(), "DATA error acquired", Toast.LENGTH_SHORT).show()
        }else{
            viewModel.fetchData(data)
        }
    }

    override fun onClickRoot(SourceId: String, SourceName: String) {}

    override fun onClickLink(link: String) {
        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(link))
        context?.startActivity(browserIntent)
    }

}