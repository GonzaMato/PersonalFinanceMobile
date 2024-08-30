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

        scaledValue.coerceAtLeast(minScale.toDouble()).toInt()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun generateWeeksForMonth(year: Int, month: Int): List<List<DayParams>> {
    val firstDayOfMonth = LocalDate.of(year, month, 1)
    val yearMonth = YearMonth.of(year, month)
    val daysInMonth = yearMonth.lengthOfMonth()

    val weeks = mutableListOf<List<DayParams>>()
    var week = mutableListOf<DayParams>()

    // Calcular el día de la semana en que comienza el mes
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value

    // Rellenar los días anteriores si el mes no comienza en lunes
    for (i in 1 until firstDayOfWeek) {
        week.add(DayParams(day = "", number = 0))  // Día vacío
    }

    for (day in 1..daysInMonth) {
        val date = LocalDate.of(year, month, day)
        val dayOfWeek = date.dayOfWeek.getDisplayName(BodyRegular, Locale.ENGLISH)

        week.add(DayParams(day = dayOfWeek, number = day))

        if (date.dayOfWeek.value == 7 || day == daysInMonth) {
            while (week.size < 7) {
                week.add(DayParams(day = "", number = 0))  // Día vacío
            }
            weeks.add(week)
            week = mutableListOf()
        }
    }

    return weeks
}