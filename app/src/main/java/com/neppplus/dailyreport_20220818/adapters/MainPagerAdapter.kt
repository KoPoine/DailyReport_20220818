package com.neppplus.dailyreport_20220818.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.neppplus.dailyreport_20220818.fragments.*

class MainPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 5
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> FeedFragment()
            1 -> StaticsFragment()
            2 -> HomeFragment()
            3 -> TimeTableFragment()
            else -> SettingFragment()
        }
    }
}