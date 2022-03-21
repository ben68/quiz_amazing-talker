package com.amazingtalker.assessment.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.amazingtalker.assessment.data.bean.Day

class DayListAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {

    private var dayList = listOf<Day>()

    fun setData(data: List<Day>) {
        dayList = data
        notifyDataSetChanged()
    }

    override fun getItemCount() = dayList.size

    override fun createFragment(position: Int) =
        DayFragment.newInstance(dayList[position])

    override fun getItemId(position: Int): Long {
        return dayList[position].id
    }

    override fun containsItem(itemId: Long): Boolean {
        for (day in dayList) {
            if (day.id == itemId) return true
        }
        return false
    }
}