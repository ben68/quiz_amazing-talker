package com.amazingtalker.assessment.data

import com.amazingtalker.assessment.data.bean.Day
import com.amazingtalker.assessment.data.bean.Week
import com.amazingtalker.assessment.domain.FormatDateUseCase

class CalendarRepository {

    fun getWeekList(startAt: FormatDateUseCase): List<Week> {
        return listOf()
    }

    fun getDayListByWeek(week: Week): List<Day> {
        return listOf()
    }
}