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
import com.neppplus.dailyreport_20220818.datas.GoalData

class GoalRecyclerAdapter(
    val mContext: Context, val mList : List<GoalData>
): RecyclerView.Adapter<GoalRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: GoalData) {
            val goalColorImg = itemView.findViewById<ImageView>(R.id.goalColorImg)
            val goalTitleTxt = itemView.findViewById<TextView>(R.id.goalTitleTxt)

            goalTitleTxt.text = item.title
            goalColorImg.background.setTint(Color.parseColor(item.color))  // Color.getColor
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val row = LayoutInflater.from(mContext).inflate(R.layout.goal_list_item, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}