package com.amazingtalker.assessment.domain

import com.amazingtalker.assessment.data.CalendarRepository
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ShowNextWeekUseCaseTest : TestCase() {

    private lateinit var repo: CalendarRepository
    private lateinit var useCase: ShowNextWeekUseCase

    @Before
    override fun setUp() {
        super.setUp()
        repo = CalendarRepository()
        useCase = ShowNextWeekUseCase(repo)
    }

    @Test
    fun testInvoke_success() {
        FormatDateUseCase(2022, 2, 26, 16, 0).let {
            runBlocking {
                useCase()
            }
        }.apply {
            assertEquals(startYear, 2022)
            assertEquals(startMonth, 2)
            assertEquals(startDay, 27)
            assertEquals(endYear, 2022)
            assertEquals(endMonth, 3)
            assertEquals(endDay, 2)
        }
    }
}