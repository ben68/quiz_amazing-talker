package com.amazingtalker.assessment.data.bean

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PeriodResponse(
    val end: String,
    val start: String
)