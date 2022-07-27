package com.mohammadhashem.lastnews.presentation.ui.fragment.secondfragment

import com.mohammadhashem.lastnews.databinding.FragmentNewsBinding
import com.mohammadhashem.lastnews.presentation.ui.base.BaseFragment

class NewsFragment : BaseFragment<FragmentNewsBinding>() {
    override fun setBinding(): FragmentNewsBinding = FragmentNewsBinding.inflate(layoutInflater)
}