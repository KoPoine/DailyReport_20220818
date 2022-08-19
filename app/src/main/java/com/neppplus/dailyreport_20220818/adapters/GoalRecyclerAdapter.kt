package com.neppplus.dailyreport_20220818.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.dailyreport_20220818.R
import com.neppplus.dailyreport_20220818.databinding.GoalListItemBinding
import com.neppplus.dailyreport_20220818.datas.GoalData

class GoalRecyclerAdapter(
    val mContext: Context, val mList : List<GoalData>
): RecyclerView.Adapter<GoalRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(
        val binding : GoalListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GoalData) {
            binding.goalTitleTxt.text = item.title
            binding.goalColorImg.background.setTint(Color.parseColor(item.color))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = GoalListItemBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}