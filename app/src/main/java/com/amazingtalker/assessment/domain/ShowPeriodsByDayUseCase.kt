package com.amazingtalker.assessment.domain

import com.amazingtalker.assessment.data.PeriodRepository
import com.amazingtalker.assessment.data.bean.Period

class ShowPeriodsByDayUseCase(private val repo: PeriodRepository) {

    suspend operator fun invoke(date: FormatDateUseCase): List<Period> {
        return repo.getPeriodListByDay(date())
    }
}