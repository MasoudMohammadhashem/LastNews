package com.mohammadhashem.lastnews.presentation.ui.fragment.mainfragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohammadhashem.lastnews.R
import com.mohammadhashem.lastnews.common.utils.imageloader.GlideImageLoader
import com.mohammadhashem.lastnews.data.model.SourceX
import com.mohammadhashem.lastnews.databinding.FragmentMainBinding
import com.mohammadhashem.lastnews.presentation.ui.adapter.listerner.OnClickAdapter
import com.mohammadhashem.lastnews.presentation.ui.adapter.sources.SourcesAdapter
import com.mohammadhashem.lastnews.presentation.ui.base.BaseFragment
import com.mohammadhashem.lastnews.presentation.ui.fragment.mainfragment.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(), OnClickAdapter {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var imageLoader: GlideImageLoader

    override fun setBinding(): FragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservables()
    }

    private fun setObservables() {
        viewModel.error.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.rvSources.visibility = View.GONE
                binding.progressCircular.visibility = View.VISIBLE
            } else {
                binding.rvSources.visibility = View.VISIBLE
                binding.progressCircular.visibility = View.GONE
            }
        })
        viewModel.sourceList.observe(viewLifecycleOwner, Observer {
            val adapter = SourcesAdapter()
            binding.rvSources.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.rvSources.adapter = adapter
            adapter.setData(it.sources,this,imageLoader)
        })
    }

    override fun onClickRoot(SourceId: String,SourceName: String) {
        val bundle = Bundle()
        val source = SourceX(SourceId,SourceName)
        bundle.putParcelable("sourceX", source)
        findNavController().navigate(R.id.action_mainFragment_to_newsFragment,bundle)
    }

    override fun onClickLink(link: String) {
        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(link))
        context?.startActivity(browserIntent)
    }
}