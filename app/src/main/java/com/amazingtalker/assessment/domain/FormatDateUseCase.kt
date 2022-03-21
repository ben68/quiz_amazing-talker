package com.amazingtalker.assessment.domain

import com.amazingtalker.assessment.data.bean.Day
import java.text.SimpleDateFormat
import java.util.*

class FormatDateUseCase {

    constructor(year: Int, month: Int, day: Int, hour: Int, minute: Int) {
        date = Calendar.getInstance().run {
            set(year, month - 1, day, hour, minute, 0)
            time
        }
    }

    constructor(dateStr: String) {
        date = getFormatter().parse(dateStr)?: Date()
    }

    constructor(date: Date) {
        this.date = date
    }

    constructor(day: Day) {
        Calendar.getInstance().apply {
            set(day.year, day.month, day.date, 0, 0, 0)
            timeZone = TimeZone.getTimeZone("GMT")
            date = time
        }
    }

    val date: Date

    private fun getFormatter(): SimpleDateFormat {
        return SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            Locale.getDefault()
        ).apply {
            timeZone = TimeZone.getTimeZone("GMT")
        }
    }

    fun toPeriod(): String {
        return SimpleDateFormat(
            "HH:mm",
            Locale.getDefault()
        ).run {
            format(date)
        }
    }

    /**
     * @return GMT, format is https://zh.wikipedia.org/zh-tw/ISO_8601
     */
    operator fun invoke(): String {
        return getFormatter().format(date)
    }
}