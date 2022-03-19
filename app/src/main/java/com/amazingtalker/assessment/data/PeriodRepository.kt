package com.amazingtalker.assessment.data

import com.amazingtalker.assessment.data.bean.Period
import com.amazingtalker.assessment.domain.FormatDateUseCase

class PeriodRepository {

    fun getPeriodListByDay(date: FormatDateUseCase): List<Period> {
        return listOf()
    }

    fun pickPeriod(date: FormatDateUseCase): Period {
        return Period(0, 0, false)
    }
}