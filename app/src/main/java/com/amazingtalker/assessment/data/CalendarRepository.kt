package com.amazingtalker.assessment.data

import com.amazingtalker.assessment.data.bean.Day
import com.amazingtalker.assessment.data.bean.Week
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class CalendarRepository {

    private lateinit var currentWeek: Week

    private fun findFirstDayOfWeek(date: Date): Date {
        return Calendar.getInstance().run {
            time = date
            while (get(Calendar.DAY_OF_WEEK) > Calendar.SUNDAY) {
                add(Calendar.DATE, -1)
            }
            time
        }
    }

    suspend fun getWeek(startAt: Date): Week {
        return withContext(Dispatchers.Default) {
            Calendar.getInstance().run {
                time = findFirstDayOfWeek(startAt)
                val startYear = get(Calendar.YEAR)
                val startMonth = get(Calendar.MONTH)
                val startDay = get(Calendar.DAY_OF_MONTH)
                add(Calendar.DATE, 6)
                val endYear = get(Calendar.YEAR)
                val endMonth = get(Calendar.MONTH)
                val endDay = get(Calendar.DAY_OF_MONTH)
                Week(startYear, endYear, startMonth, endMonth, startDay, endDay)
            }.also {
                currentWeek = it
            }
        }
    }

    suspend fun getNextWeek(): Week {
        return withContext(Dispatchers.Default) {
            Calendar.getInstance().run {
                if (::currentWeek.isInitialized) {
                    time = currentWeek.startDate()
                }
                add(Calendar.DATE, 7)
                getWeek(time)
            }.also {
                currentWeek = it
            }
        }
    }

    suspend fun getDayListByWeek(week: Week): List<Day> {
        return withContext(Dispatchers.Default) {
            mutableListOf<Day>().apply {
                with(week) {
                    val calendar = Calendar.getInstance().apply {
                        set(startYear, startMonth, startDay)
                    }
                    for (dayOfWeek in 1..7) {
                        add(Day(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            calendar.get(Calendar.DAY_OF_WEEK)
                        ))
                        calendar.add(Calendar.DATE, 1)
                    }
                }
            }.toList()
        }
    }
}