package com.amazingtalker.assessment.domain

class FormatDateUseCase(
        private val year: Int,
        private val month: Int,
        private val date: Int,
        private val time: Int
) {
    /**
     * @return format is https://zh.wikipedia.org/zh-tw/ISO_8601
     */
    operator fun invoke(): String {
        return ""
    }
}