package com.neppplus.dailyreport_20220818.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.dailyreport_20220818.R
import com.neppplus.dailyreport_20220818.adapters.GroupRecyclerAdapter
import com.neppplus.dailyreport_20220818.databinding.FragmentHomeBinding
import com.neppplus.dailyreport_20220818.datas.GroupData
import com.neppplus.dailyreport_20220818.utils.GlobalData

class HomeFragment: BaseFragment() {

    lateinit var binding : FragmentHomeBinding

    lateinit var mGroupAdapter : GroupRecyclerAdapter
    val mGroupList = ArrayList<GroupData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {
        mGroupList.addAll(GlobalData.loginUser!!.groups)
        mGroupAdapter = GroupRecyclerAdapter(mContext, mGroupList)
        binding.todoRecyclerView.adapter = mGroupAdapter
        binding.todoRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }

}