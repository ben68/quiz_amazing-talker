package com.amazingtalker.assessment.domain

import com.amazingtalker.assessment.data.PeriodRepository
import com.amazingtalker.assessment.data.bean.Period

class PickPeriodUseCase(private val repo: PeriodRepository) {

    operator fun invoke(date: FormatDateUseCase): Period {
        return repo.pickPeriod(date())
    }
}