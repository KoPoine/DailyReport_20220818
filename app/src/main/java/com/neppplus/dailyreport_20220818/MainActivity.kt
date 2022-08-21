package com.neppplus.dailyreport_20220818

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.neppplus.dailyreport_20220818.adapters.MainPagerAdapter
import com.neppplus.dailyreport_20220818.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.combine

class MainActivity : BaseActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var mPagerAdapter : MainPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {
        binding.mainViewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 -> binding.bottomNav.selectedItemId = R.id.feed
                        1 -> binding.bottomNav.selectedItemId = R.id.statics
                        2 -> binding.bottomNav.selectedItemId = R.id.home
                        3 -> binding.bottomNav.selectedItemId = R.id.table
                        4 -> binding.bottomNav.selectedItemId = R.id.setting
                    }
                }
            }
        )

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.feed -> binding.mainViewPager.currentItem = 0
                R.id.statics -> binding.mainViewPager.currentItem = 1
                R.id.home -> binding.mainViewPager.currentItem = 2
                R.id.table -> binding.mainViewPager.currentItem = 3
                R.id.setting -> binding.mainViewPager.currentItem = 4
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun setValues() {
        mPagerAdapter = MainPagerAdapter(this)
        binding.mainViewPager.adapter = mPagerAdapter

        binding.mainViewPager.currentItem = 1
        binding.bottomNav.selectedItemId = R.id.statics
    }
}