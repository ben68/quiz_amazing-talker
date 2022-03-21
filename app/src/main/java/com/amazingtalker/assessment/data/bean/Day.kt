package com.amazingtalker.assessment.data.bean

data class Day(
    val year: Int,
    val month: Int,
    val date: Int,
    val dayOfWeek: Int
) {
    val id: Long
        get() = "$year$month$date".toLong()
}