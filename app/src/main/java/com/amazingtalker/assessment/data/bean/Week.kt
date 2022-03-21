package com.amazingtalker.assessment.data.bean

import java.util.*

data class Week(
        val startYear: Int,
        val endYear: Int,
        val startMonth: Int,
        val endMonth: Int,
        val startDay: Int,
        val endDay: Int
) {
        fun startDate(): Date =
                Calendar.getInstance().apply {
                        set(startYear, startMonth, startDay)
                }.time

        fun endDate(): Date =
                Calendar.getInstance().apply {
                        set(endYear, endMonth, endDay)
                }.time
}