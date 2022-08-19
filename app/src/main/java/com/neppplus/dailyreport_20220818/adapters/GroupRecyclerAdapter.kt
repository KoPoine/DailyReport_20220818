package com.neppplus.dailyreport_20220818.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.dailyreport_20220818.R
import com.neppplus.dailyreport_20220818.datas.GroupData
import com.neppplus.dailyreport_20220818.utils.SizeUtil

class GroupRecyclerAdapter(
    val mContext : Context, val mList : List<GroupData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind (item : GroupData) {
            val titleTxt = itemView.findViewById<TextView>(R.id.titleTxt)
            val goalLayout = itemView.findViewById<LinearLayout>(R.id.goalLayout)

            titleTxt.text = item.name

            goalLayout.removeAllViews()

            for (goal in item.goals) {
                val customView = LayoutInflater.from(mContext).inflate(R.layout.goal_list_item, null)

                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(0, SizeUtil.dpToPx(mContext, 12f).toInt(), 0, 0)

                customView.layoutParams = params

                goalLayout.addView(customView)

                val goalColorImg = customView.findViewById<ImageView>(R.id.goalColorImg)
                val goalTitleTxt = customView.findViewById<TextView>(R.id.goalTitleTxt)

                goalTitleTxt.text = goal.title
                goalColorImg.background.setTint(Color.parseColor(goal.color))  // Color.getColor
            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val row = LayoutInflater.from(mContext).inflate(R.layout.group_list_item, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}