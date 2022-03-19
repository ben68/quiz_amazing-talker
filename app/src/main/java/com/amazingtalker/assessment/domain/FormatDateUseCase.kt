package com.amazingtalker.assessment.domain

import java.text.SimpleDateFormat
import java.util.*

class FormatDateUseCase(
        private val year: Int,
        private val month: Int,
        private val date: Int,
        private val hour: Int,
        private val minute: Int,
) {

    fun getDate(): Date {
        return Calendar.getInstance().run {
            set(year, month - 1, date, hour, minute, 0)
            set(Calendar.MILLISECOND, 0)
            time
        }
    }

    /**
     * @return format is https://zh.wikipedia.org/zh-tw/ISO_8601
     */
    operator fun invoke(locale: Locale): String {
        return SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            locale
        ).format(getDate())
    }
}