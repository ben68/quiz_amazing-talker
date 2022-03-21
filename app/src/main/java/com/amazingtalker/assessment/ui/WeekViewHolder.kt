package com.amazingtalker.assessment.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.amazingtalker.assessment.data.bean.Week
import com.amazingtalker.assessment.databinding.ViewWeekBinding
import java.text.SimpleDateFormat
import java.util.*

class WeekViewHolder(private val mBinding: ViewWeekBinding) : RecyclerView.ViewHolder(mBinding.root) {

    fun bind(state: Week, hasPre: Boolean, hasNext: Boolean) {
        with(mBinding) {
            txtDate.text =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    .format(state.startDate()) +
                        " ~ " +
                        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            .format(state.endDate())
            btnLeftArrow.visibility = if (hasPre) View.VISIBLE else View.INVISIBLE
            btnRightArrow.visibility = if (hasNext) View.VISIBLE else View.INVISIBLE
        }
    }
}