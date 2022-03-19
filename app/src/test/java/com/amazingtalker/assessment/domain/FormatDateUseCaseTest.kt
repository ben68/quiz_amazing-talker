package com.amazingtalker.assessment.domain

import junit.framework.TestCase
import org.junit.Test
import java.util.*

class FormatDateUseCaseTest : TestCase() {

    @Test
    fun testInvoke_success() {
        FormatDateUseCase(2022, 2, 26, 16, 0).let {
            assertEquals(it(Locale.TAIWAN), "2022-02-26T16:00:00.000Z")
        }
    }
}