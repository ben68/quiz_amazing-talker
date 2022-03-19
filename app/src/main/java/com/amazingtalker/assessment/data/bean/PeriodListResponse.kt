package com.amazingtalker.assessment.data.bean

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PeriodListResponse(
    val available: List<PeriodResponse>,
    val booked: List<PeriodResponse>
)