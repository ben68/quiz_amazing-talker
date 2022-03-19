package com.amazingtalker.assessment.domain

/**
 * @param date one day, format is https://zh.wikipedia.org/zh-tw/ISO_8601
 */
class FormatDateUseCase(private val date: String) {

    operator fun invoke(): String {
        return date
    }
}