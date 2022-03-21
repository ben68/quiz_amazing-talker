package com.amazingtalker.assessment.data

import com.amazingtalker.assessment.data.bean.Period
import com.amazingtalker.assessment.data.bean.PeriodResponse
import com.amazingtalker.assessment.data.source.remote.ApiService
import com.amazingtalker.assessment.domain.FormatDateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PeriodRepository(private val apiService: ApiService) {

    private val daysMap = mutableMapOf<String, List<Period>>()

    private fun toPeriod(data: PeriodResponse, isAvailable: Boolean): Period {
        return Period(
            data.start,
            data.end,
            isAvailable
        )
    }

    /**
     * @param date format is "yyyy-MM-dd'T'HH:mm:ss'Z'"
     */
    suspend fun getPeriodListByDay(date: String, teacherName: String = "jamie-coleman"): List<Period> {
        val dateStr = date.split("T")[0]
        return daysMap[dateStr]?:
        withContext(Dispatchers.IO) {
            apiService.getPeriodList(teacherName, date)
        }.run {
            withContext(Dispatchers.Default) {
                val bookedList = booked.map {
                    toPeriod(it, false)
                }
                available.map {
                    toPeriod(it, true)
                }.toMutableList().apply {
                    addAll(bookedList)
                }.sortedBy {
                    FormatDateUseCase(it.startAt).date
                }.let { periodList ->
                    var str = dateStr
                    var list = mutableListOf<Period>()
                    for (period in periodList) {
                        FormatDateUseCase(period.startAt)().split("T")[0].let { periodDateStr ->
                            if (periodDateStr != str) {
                                if (list.isNotEmpty())
                                    daysMap[str] = list
                                str = periodDateStr
                                list = mutableListOf()
                            }
                            else {
                                list.add(period)
                            }
                        }
                    }
                    if (list.isNotEmpty())
                        daysMap[str] = list
                }
                daysMap[dateStr]?: listOf()
            }
        }
    }

    /**
     * @param date format is "yyyy-MM-dd'T'HH:mm:ss'Z'"
     */
    fun pickPeriod(date: String): Period {
        return Period("", "", false)
    }
}