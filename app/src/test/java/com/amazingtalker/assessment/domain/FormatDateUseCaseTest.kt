package com.amazingtalker.assessment.domain

import junit.framework.TestCase
import org.junit.Test

class FormatDateUseCaseTest : TestCase() {

    @Test
    fun testInvoke_success() {
        FormatDateUseCase("2022-02-26T16:00:00Z").let {
            assertEquals(it(), "2022-02-26T16:00:00Z")
        }
        FormatDateUseCase(2022, 2, 26, 16, 0).let {
            assertEquals(it(), "2022-02-26T08:00:00Z")
        }
    }
}