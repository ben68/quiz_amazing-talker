package com.amazingtalker.assessment.domain

import com.amazingtalker.assessment.data.CalendarRepository
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ShowDaysByWeekUseCaseTest : TestCase() {

    private lateinit var useCase: ShowDaysByWeekUseCase
    private lateinit var repo: CalendarRepository

    @Before
    override fun setUp() {
        super.setUp()
        repo = CalendarRepository()
        useCase  =  ShowDaysByWeekUseCase(repo)
    }

    @Test
    fun testInvoke_success() {
        FormatDateUseCase(2022, 2, 26, 16, 0).let {
            runBlocking {
                repo.getWeek(it.date)
            }
        }.let {
            runBlocking {
                useCase(it)
            }
        }.let {
            assertEquals(it.size, 7)
            with(it.first()) {
                assertEquals(year, 2022)
                assertEquals(month, 1)
                assertEquals(date, 20)
            }
            with(it.last()) {
                assertEquals(year, 2022)
                assertEquals(month, 1)
                assertEquals(date, 26)
            }
        }
    }
}