package com.example.diseomobile.pages.grapghPage

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

    private val _movements = MutableStateFlow<List<Transaction>>(listOf())
    val movements = _movements.asStateFlow()

    private val _selectedDate = MutableStateFlow<DayOfWeek?>(null)
    val selectedDate = _selectedDate.asStateFlow()

    private val _lastWeekDay = MutableStateFlow<Date>(getSundayOfCurrentWeek())
    val lastWeekDay = _lastWeekDay.asStateFlow()

    private val _firstWeekDay = MutableStateFlow<Date>(getMondayOfCurrentWeek())
    val firstWeekDay = _firstWeekDay.asStateFlow()


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

        // Get the current day of the week
        val currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        // Adjust the calendar to the previous or current Sunday
        calendar.add(Calendar.DAY_OF_WEEK, Calendar.SUNDAY - currentDayOfWeek)

        // Then move to Monday by adding one day
        calendar.add(Calendar.DAY_OF_WEEK, 1)

        return calendar.time
    }

    private fun getSundayOfCurrentWeek(): Date {
        val calendar = Calendar.getInstance()

        // Get the current day of the week
        val currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        // Adjust the calendar to the previous or current Sunday
        calendar.add(Calendar.DAY_OF_WEEK, Calendar.SUNDAY - currentDayOfWeek)

        return calendar.time
    }

}