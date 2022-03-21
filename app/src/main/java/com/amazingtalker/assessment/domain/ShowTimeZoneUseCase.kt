package com.amazingtalker.assessment.domain

import java.util.*

class ShowTimeZoneUseCase {
    operator fun invoke(): String {
        return Calendar.getInstance().timeZone.run {
            "$displayName ${getDisplayName(false, TimeZone.SHORT)}"
        }
    }
}