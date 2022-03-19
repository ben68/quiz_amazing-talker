package com.amazingtalker.assessment.domain

import com.amazingtalker.assessment.data.CalendarRepository
import com.amazingtalker.assessment.data.bean.Week

class ShowWeeksUseCase(private val repo:CalendarRepository) {

    operator fun invoke(startAt: FormatDateUseCase): List<Week> {
        return repo.getWeekList(startAt)
    }
}