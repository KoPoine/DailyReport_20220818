package com.neppplus.dailyreport_20220818.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neppplus.dailyreport_20220818.R
import com.neppplus.dailyreport_20220818.datas.FeedData
import java.text.SimpleDateFormat

class FeedRecyclerViewAdapter(
    val mContext : Context, val mList : List<FeedData>
): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    inner class MyViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(item : FeedData) {
            val profileImg = itemView.findViewById<ImageView>(R.id.profileImg)
            val nickTxt = itemView.findViewById<TextView>(R.id.nickTxt)
            val dateTxt = itemView.findViewById<TextView>(R.id.dateTxt)
            val conTimeTxt = itemView.findViewById<TextView>(R.id.conTimeTxt)
            val contentTxt = itemView.findViewById<TextView>(R.id.contentTxt)
            val contentImg = itemView.findViewById<ImageView>(R.id.contentImg)
            val replyCountTxt = itemView.findViewById<TextView>(R.id.replyCountTxt)

            Glide.with(mContext).load(item.user.profileImg).into(profileImg)
            nickTxt.text = item.user.nickname
            val sdf = SimpleDateFormat("yy.MM.dd a h:ss")
            dateTxt.text = sdf.format(item.createdAt)
            if (item.openConTime) {
                conTimeTxt.visibility = View.VISIBLE
                conTimeTxt.text = "${item.totalConSecond}초"
            }
            contentTxt.text = item.content
            if (item.images.size > 0) {
                contentImg.visibility = View.VISIBLE
                Glide.with(mContext).load(item.images[0]).into(contentImg)
            }
            replyCountTxt.text = "댓글 ${item.replies.size}"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val row = LayoutInflater.from(mContext).inflate(R.layout.feed_list_item, parent, false)
        return MyViewHolder(row)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

}