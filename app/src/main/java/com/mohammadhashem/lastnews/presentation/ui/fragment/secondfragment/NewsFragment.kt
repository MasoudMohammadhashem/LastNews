package com.mohammadhashem.lastnews.presentation.ui.fragment.secondfragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohammadhashem.lastnews.common.utils.imageloader.GlideImageLoader
import com.mohammadhashem.lastnews.data.model.Article
import com.mohammadhashem.lastnews.data.model.SourceX
import com.mohammadhashem.lastnews.databinding.FragmentNewsBinding
import com.mohammadhashem.lastnews.presentation.ui.adapter.articles.ArticlePagingAdapter
import com.mohammadhashem.lastnews.presentation.ui.adapter.listerner.OnClickAdapter
import com.mohammadhashem.lastnews.presentation.ui.base.BaseFragment
import com.mohammadhashem.lastnews.presentation.ui.fragment.secondfragment.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import org.reactivestreams.Subscriber
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : BaseFragment<FragmentNewsBinding>(), OnClickAdapter {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var mAdapter: ArticlePagingAdapter

    @Inject
    lateinit var imageLoader: GlideImageLoader

    override fun setBinding(): FragmentNewsBinding = FragmentNewsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservables()
        gettingExtras()

        mAdapter = ArticlePagingAdapter()

        binding.rvArticles.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        mAdapter.setData(this, imageLoader)
        binding.rvArticles.adapter = mAdapter

        compositeDisposable.add(viewModel.callRemote(gettingExtras())
            .subscribe {
                mAdapter.submitData(lifecycle, it)
            })

    }

    private fun setObservables() {
        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun gettingExtras(): SourceX {
        val data = arguments?.getParcelable<SourceX>("sourceX")
        if (data == null) {
            Toast.makeText(requireContext(), "DATA error acquired", Toast.LENGTH_SHORT).show()
        } else {
            return data
        }
        return SourceX("", "")
    }

    override fun onClickRoot(SourceId: String, SourceName: String) {/*An Action*/}

    override fun onClickLink(link: String) {
        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(link))
        context?.startActivity(browserIntent)
    }

}