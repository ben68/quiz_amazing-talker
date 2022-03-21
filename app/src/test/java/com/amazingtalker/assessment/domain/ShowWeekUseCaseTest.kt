package com.amazingtalker.assessment.domain

import com.amazingtalker.assessment.data.CalendarRepository
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ShowWeekUseCaseTest : TestCase() {

    private lateinit var repo: CalendarRepository
    private lateinit var useCase: ShowWeekUseCase

    @Before
    override fun setUp() {
        super.setUp()
        repo =  CalendarRepository()
        useCase = ShowWeekUseCase(repo)
    }

    @Test
    fun testInvoke_success() {
        FormatDateUseCase(2022, 2, 26, 16, 0).let {
            runBlocking {
                repo.getWeek(it.date)
            }
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