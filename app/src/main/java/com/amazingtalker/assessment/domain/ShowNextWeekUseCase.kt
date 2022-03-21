package com.amazingtalker.assessment.domain

import com.amazingtalker.assessment.data.CalendarRepository
import com.amazingtalker.assessment.data.bean.Week

class ShowNextWeekUseCase(private val repo: CalendarRepository) {

    suspend operator fun invoke(): Week {
        return repo.getNextWeek()
    }
}