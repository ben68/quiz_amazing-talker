package com.amazingtalker.assessment.domain

import com.amazingtalker.assessment.data.CalendarRepository
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class ShowDaysByWeekUseCaseTest : TestCase() {

    private lateinit var showDaysByWeekUseCase: ShowDaysByWeekUseCase
    private lateinit var repository: CalendarRepository

    @Before
    override fun setUp() {
        showDaysByWeekUseCase  =  ShowDaysByWeekUseCase(repository)
    }

    @Test
    fun testInvoke_success() {
        FormatDateUseCase(2022, 2, 26, 16).let {
            repository.getWeekList(it).first()
        }.let {
            showDaysByWeekUseCase(it)
        }.let {
            with(it.first()) {
                assertEquals(year, 2022)
                assertEquals(month, 2)
                assertEquals(date, 20)
            }
            with(it.last()) {
                assertEquals(year, 2022)
                assertEquals(month, 2)
                assertEquals(date, 26)
            }
        }
    }
}