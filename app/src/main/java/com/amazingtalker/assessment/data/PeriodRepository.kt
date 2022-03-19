package com.amazingtalker.assessment.data

import com.amazingtalker.assessment.data.bean.Period
import com.amazingtalker.assessment.data.bean.PeriodResponse
import com.amazingtalker.assessment.data.source.remote.ApiService
import com.amazingtalker.assessment.domain.FormatDateUseCase

class PeriodRepository(private val apiService: ApiService) {

    private fun toPeriod(data: PeriodResponse, isAvailable: Boolean): Period {
        return Period(
            data.start,
            data.end,
            isAvailable
        )
    }

    fun getPeriodListByDay(teacherName: String, date: FormatDateUseCase): List<Period> {
        return apiService.getPeriodList(teacherName, date()).run {
            val bookedList = booked.map {
                toPeriod(it, false)
            }
            available.map {
                toPeriod(it, true)
            }.toMutableList().apply {
                addAll(bookedList)
            }.sortedBy {
                FormatDateUseCase(it.startAt).date
            }
        }
    }

    fun pickPeriod(date: FormatDateUseCase): Period {
        return Period("", "", false)
    }
}