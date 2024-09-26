package com.example.diseomobile.utils

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.TextStyle
import com.example.diseomobile.Components.Calendar.Week.DayParams
import com.example.diseomobile.Components.RecentActivity.MovementParams
import com.example.diseomobile.ui.theme.BodyRegular
import java.time.LocalDate
import java.time.YearMonth
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.log10

enum class DayOfWeek {
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday,
    Sunday
}



fun SeparateByWeek(movements: List<MovementParams>): Map<DayOfWeek, Int> {
    val week = mutableMapOf<DayOfWeek, Int>()
    DayOfWeek.entries.forEach {
        week[it] = 0
    }
    movements.forEach {
        val day = Calendar.getInstance().apply { time = it.date }
        val dayOfWeek = when (day.get(Calendar.DAY_OF_WEEK)) {
            Calendar.MONDAY -> DayOfWeek.Monday
            Calendar.TUESDAY -> DayOfWeek.Tuesday
            Calendar.WEDNESDAY -> DayOfWeek.Wednesday
            Calendar.THURSDAY -> DayOfWeek.Thursday
            Calendar.FRIDAY -> DayOfWeek.Friday
            Calendar.SATURDAY -> DayOfWeek.Saturday
            Calendar.SUNDAY -> DayOfWeek.Sunday
            else -> throw Exception("Invalid day of the week")
        }
        val dayOfTheWeekWithLength = dayOfWeek

        week[dayOfTheWeekWithLength] = week.getOrDefault(dayOfTheWeekWithLength, 0) + it.amount.toInt()
    }
    return week
}


fun getLengthForWeekDays(week: Map<DayOfWeek, Int>, minScale: Int = 20): Map<DayOfWeek, Int> {
    val max = week.values.maxOrNull() ?: 1

    return week.mapValues { (_, value) ->

        val logMax = log10(max.toDouble() + 1)
        val logValue = log10(value.toDouble() + 1)

        val scaledValue = (logValue / logMax) * 100


        val valueA = scaledValue.coerceAtLeast(minScale.toDouble()).toInt()

        if (valueA == 0) {
            10
        } else {
            valueA
        }
    }
}

fun getWeeksForMonth(lastDay: Date, amountOfWeeks: Int): List<List<DayParams>> {
    val weeks = mutableListOf<List<DayParams>>()
    val lastDayCalendar = Calendar.getInstance().apply { time = lastDay }
    var currentDay = Calendar.getInstance().apply { time = lastDay }

    currentDay.add(Calendar.DAY_OF_MONTH, -(amountOfWeeks * 7 - 1))

    for (week in 1..amountOfWeeks) {
        val thisWeek = mutableListOf<DayParams>()

        for (day in 0..6) {
            val isSameMonth = currentDay.get(Calendar.MONTH) == lastDayCalendar.get(Calendar.MONTH)
            val dayOfWeek = currentDay.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())
            dayOfWeek?.let {
                if (isSameMonth) {
                    thisWeek.add(DayParams(it, currentDay.get(Calendar.DAY_OF_MONTH)))
                } else {
                    thisWeek.add(DayParams(it, currentDay.get(Calendar.DAY_OF_MONTH), false))
                }
            }
            currentDay.add(Calendar.DAY_OF_MONTH, 1)
        }

        weeks.add(thisWeek)
    }

    return weeks
}

fun getMonthByDate(date: Date): String {
    val calendar = Calendar.getInstance().apply { time = date }
    return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())?.take(3) ?: ""
}

fun getLastDayOfPreviousMonth(currentDate: Date): Date {
    val calendar = Calendar.getInstance()
    calendar.time = currentDate
    calendar.add(Calendar.MONTH, -1)
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
    return calendar.time
}

fun getLastDayOfNextMonth(currentDate: Date): Date {
    val calendar = Calendar.getInstance()
    calendar.time = currentDate
    calendar.add(Calendar.MONTH, 1)
    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH))
    return calendar.time
}