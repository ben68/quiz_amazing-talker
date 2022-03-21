package com.amazingtalker.assessment.domain

import com.amazingtalker.assessment.data.CalendarRepository
import com.amazingtalker.assessment.data.bean.Week

class ShowWeekUseCase(private val repo:CalendarRepository) {

    suspend operator fun invoke(startAt: FormatDateUseCase): Week {
        return repo.getWeek(startAt.date)
    }
}