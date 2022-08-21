package com.neppplus.dailyreport_20220818.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.neppplus.dailyreport_20220818.R
import com.neppplus.dailyreport_20220818.adapters.ChattingRecyclerAdapter
import com.neppplus.dailyreport_20220818.databinding.FragmentStaticsBinding
import com.neppplus.dailyreport_20220818.datas.ChattingData
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class StaticsFragment: BaseFragment() {

    lateinit var binding : FragmentStaticsBinding
    val database = FirebaseDatabase.getInstance("https://dailyreport-20220818-default-rtdb.asia-southeast1.firebasedatabase.app/")

    lateinit var mAdapter : ChattingRecyclerAdapter
    val mList = ArrayList<ChattingData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_statics, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.sendBtn.setOnClickListener {
//            서버에 데이터 기록
            val inputContent = binding.contentEdt.text.toString()
            val now = Calendar.getInstance()

            val sdf = SimpleDateFormat("a h:ss")

//            맵 구조 활용 한번에 서버에 전송

            database.getReference("message").child("content").setValue(inputContent)
            database.getReference("message").child("date").setValue(sdf.format(now.time))

            binding.contentEdt.setText("")
        }

//        항목의 데이터 변경 감지
        database.getReference("message")
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    Log.d("message 항목 값", snapshot.value.toString())

                    mList.add(0,
                        ChattingData(
                            snapshot.child("content").value.toString(),
                            snapshot.child("date").value.toString()
                        )
                    )
                    mAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

    override fun setValues() {
        mAdapter = ChattingRecyclerAdapter(mContext, mList)
        binding.chattingRecyclerView.adapter = mAdapter
        binding.chattingRecyclerView.layoutManager = LinearLayoutManager(mContext)
    }

}