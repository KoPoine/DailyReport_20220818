package com.neppplus.dailyreport_20220818.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.dailyreport_20220818.databinding.ChattingListItemBinding
import com.neppplus.dailyreport_20220818.datas.ChattingData

class ChattingRecyclerAdapter(
    val mContext : Context, val mList : List<ChattingData>
): RecyclerView.Adapter<ChattingRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding : ChattingListItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ChattingData) {
            binding.contentTxt.text = item.content
            binding.dateTxt.text = item.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ChattingListItemBinding.inflate(LayoutInflater.from(mContext), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}