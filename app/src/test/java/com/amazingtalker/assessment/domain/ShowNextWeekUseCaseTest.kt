package com.amazingtalker.assessment.domain

import com.amazingtalker.assessment.data.CalendarRepository
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class ShowNextWeekUseCaseTest : TestCase() {

    private lateinit var repository: CalendarRepository
    private lateinit var useCase: ShowNextWeekUseCase

    @Before
    override fun setUp() {
        super.setUp()
        repository = CalendarRepository()
        useCase = ShowNextWeekUseCase(repository)
    }

    @Test
    fun testInvoke_success() {
        FormatDateUseCase(2022, 2, 26, 16, 0).let {
            useCase(it)
        }.apply {
            assertEquals(startYear, 2022)
            assertEquals(startMonth, 1)
            assertEquals(startDay, 27)
            assertEquals(endYear, 2022)
            assertEquals(endMonth, 2)
            assertEquals(endDay, 5)
        }
    }
}