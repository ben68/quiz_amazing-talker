package com.amazingtalker.assessment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amazingtalker.assessment.data.bean.Week
import com.amazingtalker.assessment.databinding.ViewWeekBinding

class WeekAdapter(private val mViewModel: CalendarViewModel) : RecyclerView.Adapter<WeekViewHolder>() {

    private var data = listOf<Week>()

    fun setData(weekData: List<Week>) {
        data = weekData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WeekViewHolder(
            ViewWeekBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).apply {
                btnLeftArrow.setOnClickListener {
                    mViewModel.showPreviousWeek()
                }
                btnRightArrow.setOnClickListener {
                    mViewModel.showNextWeek()
                }
            }
        )

    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {
        holder.bind(data[position], position > 0, position < itemCount - 1)
        if (position == itemCount - 1) {
            mViewModel.getNextWeekData()
        }
    }

    override fun getItemCount() = data.size
}