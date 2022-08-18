package com.neppplus.dailyreport_20220818.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.neppplus.dailyreport_20220818.R
import com.neppplus.dailyreport_20220818.databinding.FragmentFeedBinding
import com.neppplus.dailyreport_20220818.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedFragment: BaseFragment() {

    lateinit var binding : FragmentFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.fab.setOnClickListener{

        }
    }

    override fun setValues() {
        getFeedDataFromServer(0)
    }

    fun getFeedDataFromServer(pageNum: Int) {
        apiList.getRequestFeed(pageNum).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {

                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }
}