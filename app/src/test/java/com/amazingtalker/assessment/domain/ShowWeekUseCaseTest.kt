package com.amazingtalker.assessment.domain

import com.amazingtalker.assessment.data.CalendarRepository
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class ShowWeekUseCaseTest : TestCase() {

    private lateinit var repository: CalendarRepository
    private lateinit var showWeekUseCase: ShowWeekUseCase

    @Before
    override fun setUp() {
        super.setUp()
        repository =  CalendarRepository()
        showWeekUseCase = ShowWeekUseCase(repository)
    }

    @Test
    fun testInvoke_success() {
        FormatDateUseCase(2022, 2, 26, 16, 0).let {
            repository.getWeek(it)
        }.let {
            with(it) {
                assertEquals(endDay - startDay, 6)
                assertEquals(startYear, 2022)
                assertEquals(startMonth, 1)
                assertEquals(startDay, 20)
                assertEquals(endYear, 2022)
                assertEquals(endMonth, 1)
                assertEquals(endDay, 26)
            }
        }
    }
}