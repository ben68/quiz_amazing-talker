package com.amazingtalker.assessment.domain

import java.text.SimpleDateFormat
import java.util.*

class FormatDateUseCase {

    constructor(year: Int, month: Int, day: Int, hour: Int, minute: Int) {
        date = Calendar.getInstance().run {
            set(year, month - 1, day, hour, minute, 0)
            set(Calendar.MILLISECOND, 0)
            time
        }
    }

    constructor(dateStr: String) {
        date = getFormatter().parse(dateStr)?: Date()
    }

    val date: Date

    private fun getFormatter(): SimpleDateFormat {
        return SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            Locale.getDefault()
        )
    }

    /**
     * @return format is https://zh.wikipedia.org/zh-tw/ISO_8601
     */
    operator fun invoke(): String {
        return getFormatter().format(date)
    }
}