package com.example.diseomobile.pages.graphPage

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diseomobile.data.WiseRipOffDatabase
import com.example.diseomobile.data.models.transaction.Transaction
import com.example.diseomobile.utils.DayOfWeek
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ViewModelGraphPage @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val wiseRipOffDatabase = WiseRipOffDatabase.getDataBase(context)

    private val _selectedDate = MutableStateFlow<DayOfWeek?>(null)
    val selectedDate = _selectedDate.asStateFlow()

    private val _lastWeekDay = MutableStateFlow<Date>(getSundayOfCurrentWeek())
    val lastWeekDay = _lastWeekDay.asStateFlow()

    private val _firstWeekDay = MutableStateFlow<Date>(getMondayOfCurrentWeek())
    val firstWeekDay = _firstWeekDay.asStateFlow()

    private val _movements = MutableStateFlow<List<Transaction>>(listOf())
    val movements = _movements.asStateFlow()

    fun loadMovementsOfTheWeek() {
        viewModelScope.launch {
            _movements.value = wiseRipOffDatabase.transactionDao().getTransactionsInRange(
                _firstWeekDay.value,
                _lastWeekDay.value
            )
            println("Movements: ${_movements.value}")
        }
    }

    fun setFirstWeekDay(date: Date) {
        _firstWeekDay.value = date
    }

    fun setLastWeekDay(date: Date) {
        _lastWeekDay.value = date
    }

    fun setSelectedDate(dayOfWeek: DayOfWeek) {
        _selectedDate.value = dayOfWeek
    }


    private fun getMondayOfCurrentWeek(): Date {
        val calendar = Calendar.getInstance()

        // Set the calendar to the beginning of the week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

        // Ensure time is set to the beginning of the day
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        return calendar.time
    }

    private fun getSundayOfCurrentWeek(): Date {
        val calendar = Calendar.getInstance()

        // Set the calendar to the current day of the week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)

        // If today is Sunday, move to the next Sunday
        if (calendar.time.before(Calendar.getInstance().time)) {
            calendar.add(Calendar.WEEK_OF_YEAR, 1)
        }

        // Ensure time is set to the end of the day
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)

        return calendar.time
    }
}