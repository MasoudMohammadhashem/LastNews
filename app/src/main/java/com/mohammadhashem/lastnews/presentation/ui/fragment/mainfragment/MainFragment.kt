package com.mohammadhashem.lastnews.presentation.ui.fragment.mainfragment

import com.mohammadhashem.lastnews.databinding.FragmentMainBinding
import com.mohammadhashem.lastnews.presentation.ui.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(){
    override fun setBinding(): FragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
}