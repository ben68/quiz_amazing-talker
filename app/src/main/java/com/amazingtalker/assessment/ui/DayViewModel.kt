package com.amazingtalker.assessment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amazingtalker.assessment.data.bean.Period
import com.amazingtalker.assessment.domain.FormatDateUseCase
import com.amazingtalker.assessment.domain.ShowPeriodsByDayUseCase
import kotlinx.coroutines.launch

class DayViewModel(private val showPeriodsByDayUseCase: ShowPeriodsByDayUseCase): ViewModel() {

    private val _periodListState = MutableLiveData<List<Period>>(listOf())
    val periodListState: LiveData<List<Period>>
        get() = _periodListState

    fun getPeriodListData(date: FormatDateUseCase) {
        viewModelScope.launch {
            showPeriodsByDayUseCase(date).let {
                _periodListState.value = it
            }
        }
    }
}