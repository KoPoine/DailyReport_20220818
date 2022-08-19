package com.neppplus.dailyreport_20220818.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.neppplus.dailyreport_20220818.R
import com.neppplus.dailyreport_20220818.databinding.FragmentFirstBinding

class FirstFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFirstBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)
        return binding.root
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}