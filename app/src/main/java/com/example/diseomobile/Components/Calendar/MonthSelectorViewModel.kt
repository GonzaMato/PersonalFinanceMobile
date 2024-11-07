package com.example.diseomobile.Components.Calendar

import androidx.lifecycle.ViewModel
import com.example.diseomobile.utils.getLastDayOfNextMonth
import com.example.diseomobile.utils.getLastDayOfPreviousMonth
import com.example.diseomobile.utils.getLastDayOfThisMonth
import com.example.diseomobile.utils.getWeeksForMonth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MonthSelectorViewModel @Inject constructor(

) : ViewModel() {
    private val _amountOfWeeks = 5
    val amountOfWeeks: Int
        get() = _amountOfWeeks

    private val _lastDayOfCalendar = MutableStateFlow(getLastDayOfThisMonth(Date()))
    val lastDayOfCalendar = _lastDayOfCalendar.asStateFlow()

    private val _calendarWeeks = MutableStateFlow(
        getWeeksForMonth(_lastDayOfCalendar.value, _amountOfWeeks)
    )
    val calendarWeeks = _calendarWeeks.asStateFlow()

    private val _selectedWeek = MutableStateFlow(_amountOfWeeks - 1)
    val selectedWeek = _selectedWeek.asStateFlow()

    fun updateToPreviousMonth() {
        _lastDayOfCalendar.value = getLastDayOfPreviousMonth(_lastDayOfCalendar.value)
        _calendarWeeks.value = getWeeksForMonth(_lastDayOfCalendar.value, _amountOfWeeks)
    }

    fun updateToNextMonth() {
        _lastDayOfCalendar.value = getLastDayOfNextMonth(_lastDayOfCalendar.value)
        _calendarWeeks.value = getWeeksForMonth(_lastDayOfCalendar.value, _amountOfWeeks)
    }

    fun setSelectedWeek(weekIndex: Int) {
        _selectedWeek.value = weekIndex
    }
}
