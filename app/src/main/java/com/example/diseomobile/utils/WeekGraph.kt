package com.example.diseomobile.utils

import com.example.diseomobile.Components.RecentActivity.MovementParams
import java.util.Calendar
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

        scaledValue.coerceAtLeast(minScale.toDouble()).toInt()
    }
}



