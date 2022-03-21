package com.amazingtalker.assessment.domain

import com.amazingtalker.assessment.data.CalendarRepository
import com.amazingtalker.assessment.data.bean.Day
import com.amazingtalker.assessment.data.bean.Week

class ShowDaysByWeekUseCase(private val repo: CalendarRepository) {

    suspend operator fun invoke(week: Week): List<Day> {
        return repo.getDayListByWeek(week)
    }
}