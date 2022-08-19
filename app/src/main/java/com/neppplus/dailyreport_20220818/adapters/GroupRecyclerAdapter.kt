package com.neppplus.dailyreport_20220818.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.dailyreport_20220818.MainActivity
import com.neppplus.dailyreport_20220818.R
import com.neppplus.dailyreport_20220818.datas.GroupData
import com.neppplus.dailyreport_20220818.fragments.FirstFragment
import com.neppplus.dailyreport_20220818.fragments.HomeFragment
import com.neppplus.dailyreport_20220818.fragments.SecondFragment
import com.neppplus.dailyreport_20220818.utils.GlobalData
import com.neppplus.dailyreport_20220818.utils.SizeUtil
import retrofit2.http.Header

class GroupRecyclerAdapter(
    val mContext : Context, val mList : List<GroupData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val TYPE_HEADER = 0
    val TYPE_ITEM = 1
    val TYPE_FOOTER = 2

    val homeFragment = (mContext as MainActivity)
        .supportFragmentManager
        .findFragmentByTag("f2") as HomeFragment

    inner class FooterViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind () {
            val btn = itemView.findViewById<Button>(R.id.btn)

            btn.setOnClickListener {
                (mContext as MainActivity).binding.mainViewPager.currentItem = 1
            }
        }
    }

    inner class HeaderViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind () {
//            val nickTxt = itemView.findViewById<TextView>(R.id.nickTxt)
            val radioBtn = itemView.findViewById<RadioButton>(R.id.radioBtn1)
            val radioGroup = itemView.findViewById<RadioGroup>(R.id.radioGroup)

//            nickTxt.text = GlobalData.loginUser!!.nickname

            radioBtn.isChecked = true


            homeFragment.childFragmentManager.beginTransaction()
                .add(R.id.conFragLayout, FirstFragment()).commit()

            radioGroup.setOnCheckedChangeListener { radioGroup, i ->
                when (i) {
                    R.id.radioBtn1 -> {
//                        radioButton 클릭 이벤트 처리 진행
                        homeFragment.childFragmentManager.beginTransaction()
                            .replace(R.id.conFragLayout, FirstFragment()).commit()
                    }
                    R.id.radioBtn2 -> {
                        homeFragment.childFragmentManager.beginTransaction()
                            .replace(R.id.conFragLayout, SecondFragment()).commit()
                    }
                }
            }
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind (item : GroupData) {
            val titleTxt = itemView.findViewById<TextView>(R.id.titleTxt)
            val goalLayout = itemView.findViewById<LinearLayout>(R.id.goalLayout)
            val goalRecyclerview = itemView.findViewById<RecyclerView>(R.id.goalRecyclerView)

            titleTxt.text = item.name

            val goalList = item.goals
            val goalRecyclerViewAdapter = GoalRecyclerAdapter(mContext, goalList)
            goalRecyclerview.adapter = goalRecyclerViewAdapter
            goalRecyclerview.layoutManager = LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL, false)

//            goalLayout.removeAllViews()

//            for (goal in item.goals) {
//                val customView = LayoutInflater.from(mContext).inflate(R.layout.goal_list_item, null)
//
//                val params = LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//                params.setMargins(0, SizeUtil.dpToPx(mContext, 12f).toInt(), 0, 0)
//
//                customView.layoutParams = params
//
//                goalLayout.addView(customView)
//
//                val goalColorImg = customView.findViewById<ImageView>(R.id.goalColorImg)
//                val goalTitleTxt = customView.findViewById<TextView>(R.id.goalTitleTxt)
//
//                goalTitleTxt.text = goal.title
//                goalColorImg.background.setTint(Color.parseColor(goal.color))  // Color.getColor
//            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_HEADER -> {
                val row = LayoutInflater.from(mContext).inflate(R.layout.group_header_list_item, parent, false)
                HeaderViewHolder(row)
            }
            TYPE_FOOTER -> {
                val row = LayoutInflater.from(mContext).inflate(R.layout.group_footer_list_item, parent, false)
                FooterViewHolder(row)
            }
            else -> {
                val row = LayoutInflater.from(mContext).inflate(R.layout.group_list_item, parent, false)
                MyViewHolder(row)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is HeaderViewHolder -> holder.bind()
            is MyViewHolder -> holder.bind(mList[position - 1])
            is FooterViewHolder -> holder.bind()
        }
    }

    override fun getItemCount(): Int {
        return mList.size + 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_HEADER
            mList.size + 1 -> TYPE_FOOTER
            else -> TYPE_ITEM
        }
    }

}