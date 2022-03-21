package com.amazingtalker.assessment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amazingtalker.assessment.data.bean.Day
import com.amazingtalker.assessment.data.bean.Week
import com.amazingtalker.assessment.domain.*
import kotlinx.coroutines.launch
import java.util.*

class CalendarViewModel(
    private val showWeekUseCase: ShowWeekUseCase,
    private val showNextWeekUseCase: ShowNextWeekUseCase,
    private val showDaysByWeekUseCase: ShowDaysByWeekUseCase,
    private val showTimeZoneUseCase: ShowTimeZoneUseCase
): ViewModel() {

    private val _timeZoneState = MutableLiveData<String>()
    val timeZoneState: LiveData<String>
        get() = _timeZoneState

    private val _weekState = MutableLiveData<List<Week>>(listOf())
    val weekState: LiveData<List<Week>>
        get() = _weekState

    private val _dayListState = MutableLiveData<List<Day>>(listOf())
    val dayListState: LiveData<List<Day>>
        get() = _dayListState

    private val _shouldShowNextWeek = MutableLiveData<Boolean>(false)
    val shouldShowNextWeek: LiveData<Boolean>
        get() = _shouldShowNextWeek

    private val _shouldShowPreviousWeek = MutableLiveData<Boolean>(false)
    val shouldShowPreviousWeek: LiveData<Boolean>
        get() = _shouldShowPreviousWeek

    private fun appendWeekData(week: Week) {
        _weekState.value =
            _weekState.value?.toMutableList()?.run {
                add(week)
                toList()
            }
    }

    fun getDayListDataByWeek(position: Int = 0) {
        weekState.value?.get(position)?.let {
            viewModelScope.launch {
                _dayListState.value = showDaysByWeekUseCase(it)
            }
        }
    }

    fun getWeekData() {
        Calendar.getInstance().run {
            FormatDateUseCase(time)
        }.let { date ->
            viewModelScope.launch {
                appendWeekData(
                    showWeekUseCase(date)
                )
            }
        }
    }

    fun getTimeZone() {
        _timeZoneState.value = showTimeZoneUseCase()
    }

    fun getNextWeekData() {
        viewModelScope.launch {
            appendWeekData(showNextWeekUseCase())
        }
    }

    fun showPreviousWeek() {
        _shouldShowPreviousWeek.value = true
    }

    fun showPreviousWeekIsDone() {
        _shouldShowPreviousWeek.value = false
    }

    fun showNextWeek() {
        _shouldShowNextWeek.value = true
    }

    fun showNextWeekIsDone() {
        _shouldShowNextWeek.value = false
    }
}