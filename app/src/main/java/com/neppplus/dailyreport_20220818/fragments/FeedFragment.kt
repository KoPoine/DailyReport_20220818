package com.neppplus.dailyreport_20220818.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.dailyreport_20220818.R
import com.neppplus.dailyreport_20220818.adapters.FeedRecyclerViewAdapter
import com.neppplus.dailyreport_20220818.databinding.FragmentFeedBinding
import com.neppplus.dailyreport_20220818.datas.BasicResponse
import com.neppplus.dailyreport_20220818.datas.FeedData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedFragment: BaseFragment() {

    lateinit var binding : FragmentFeedBinding
    lateinit var mFeedRecyclerAdapter : FeedRecyclerViewAdapter
    val mFeedList = ArrayList<FeedData>()

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
        mFeedRecyclerAdapter = FeedRecyclerViewAdapter(mContext, mFeedList)
        binding.feedRecyclerView.adapter = mFeedRecyclerAdapter
        binding.feedRecyclerView.layoutManager = LinearLayoutManager(mContext)
        binding.feedRecyclerView.addOnScrollListener(scrollListener)

        getFeedDataFromServer(0)
    }

    fun getFeedDataFromServer(pageNum: Int) {
        apiList.getRequestFeed(pageNum).enqueue(object : Callback<BasicResponse>{
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                if (response.isSuccessful) {
                    val br = response.body()!!
                    mFeedList.clear()
                    mFeedList.addAll(br.data.feeds)
                    mFeedRecyclerAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }
        })
    }

    val scrollListener = object : RecyclerView.OnScrollListener(){
//        지금 리싸이클러뷰가 움직일때 어느 위치에 있는가? 판단
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (recyclerView.canScrollVertically(1) &&
                newState == RecyclerView.SCROLL_STATE_SETTLING
            ) {
//                리싸이클러뷰 최상단
//                첫 페이지(pageNum = 0) 피드로 갱신하기
                Log.d("현재 상태", "최상단")
            }
            else if (recyclerView.canScrollVertically(-1) &&
                newState == RecyclerView.SCROLL_STATE_SETTLING) {
//                리싸이클러뷰 최하단
//                다음페이지의 피드 받아오기(page + 1)
                Log.d("현재 상태", "최하단")
            }
            else {
//                리싸이클러 뷰 작동 중
            }
        }
    }
}