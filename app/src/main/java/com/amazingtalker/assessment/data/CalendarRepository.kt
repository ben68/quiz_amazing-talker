package com.amazingtalker.assessment.data

import com.amazingtalker.assessment.data.bean.Day
import com.amazingtalker.assessment.data.bean.Week
import com.amazingtalker.assessment.domain.FormatDateUseCase
import java.util.*

class CalendarRepository {

    private fun findFirstDayOfWeek(date: Date): Date {
        return Calendar.getInstance().run {
            time = date
            while (get(Calendar.DAY_OF_WEEK) > Calendar.SUNDAY) {
                add(Calendar.DATE, -1)
            }
            time
        }
    }

    fun getWeek(startAt: FormatDateUseCase): Week {
        val date = startAt.getDate()
        return Calendar.getInstance().run {
            time = findFirstDayOfWeek(date)
            val startYear = get(Calendar.YEAR)
            val startMonth = get(Calendar.MONTH)
            val startDay = get(Calendar.DAY_OF_MONTH)
            add(Calendar.DATE, 6)
            val endYear = get(Calendar.YEAR)
            val endMonth = get(Calendar.MONTH)
            val endDay = get(Calendar.DAY_OF_MONTH)
            Week(startYear, endYear, startMonth, endMonth, startDay, endDay)
        }
    }

    fun getNextWeek(startAt: FormatDateUseCase): Week {
        val date = startAt.getDate()
        return Calendar.getInstance().run {
            time = date
            add(Calendar.DATE, 7)
            getWeek(FormatDateUseCase(
                    get(Calendar.YEAR),
                    get(Calendar.MONTH) + 1,
                    get(Calendar.DAY_OF_MONTH),
                    get(Calendar.HOUR),
                    get(Calendar.MINUTE)
            ))
        }
    }

    fun getDayListByWeek(week: Week): List<Day> {
        return mutableListOf<Day>().apply {
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