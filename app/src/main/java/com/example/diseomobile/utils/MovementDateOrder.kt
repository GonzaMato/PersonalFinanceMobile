package com.example.diseomobile.utils

import com.example.diseomobile.Components.RecentActivity.MovementParams
import java.text.SimpleDateFormat
import java.util.*


fun categorizeMovementsByDate(
    dateStrings: Map<String, String>,
    movements: List<MovementParams>
): Map<String, List<MovementParams>> {
    val today = Calendar.getInstance()

    val categorizedMovements = movements
        .sortedByDescending { it.date }
        .groupBy { movement ->
            val movementDate = Calendar.getInstance().apply { time = movement.date }

            when {
                isSameDay(today, movementDate) -> dateStrings.get("Today").orEmpty()
                isThisWeek(movementDate) -> dateStrings.get("ThisWeek").orEmpty()
                isLastWeek(movementDate) -> dateStrings.get("LastWeek").orEmpty()
                isSameMonth(today, movementDate) -> dateStrings.get("ThisMonth").orEmpty()
                isLastMonth(movementDate) -> dateStrings.get("LastMonth").orEmpty()
                isSameYear(today, movementDate) -> dateStrings.get("ThisYear").orEmpty()
                else -> SimpleDateFormat("yyyy", Locale.getDefault()).format(movement.date)
            }
        }

    return categorizedMovements
}

private fun isSameDay(today: Calendar, other: Calendar): Boolean {
    return today.get(Calendar.YEAR) == other.get(Calendar.YEAR) &&
            today.get(Calendar.DAY_OF_YEAR) == other.get(Calendar.DAY_OF_YEAR)
}

private fun isThisWeek(other: Calendar): Boolean {
    val today = Calendar.getInstance()

    val startOfThisWeek = today.clone() as Calendar
    startOfThisWeek.set(Calendar.DAY_OF_WEEK, startOfThisWeek.firstDayOfWeek)

    return !isSameDay(today, other) && other.after(startOfThisWeek) && other.before(today)
}

private fun isLastWeek(other: Calendar): Boolean {
    val today = Calendar.getInstance()

    val startOfThisWeek = today.clone() as Calendar
    startOfThisWeek.set(Calendar.DAY_OF_WEEK, startOfThisWeek.firstDayOfWeek)

    val startOfLastWeek = startOfThisWeek.clone() as Calendar
    startOfLastWeek.add(Calendar.WEEK_OF_YEAR, -1)

    val endOfLastWeek = startOfThisWeek.clone() as Calendar
    endOfLastWeek.add(Calendar.DAY_OF_YEAR, -1)

    return other.after(startOfLastWeek) && other.before(endOfLastWeek)
}

private fun isSameMonth(today: Calendar, other: Calendar): Boolean {
    return today.get(Calendar.YEAR) == other.get(Calendar.YEAR) &&
            today.get(Calendar.MONTH) == other.get(Calendar.MONTH)
}

private fun isLastMonth(other: Calendar): Boolean {
    val today = Calendar.getInstance().apply {
        add(Calendar.MONTH, -1)
    }
    return today.get(Calendar.YEAR) == other.get(Calendar.YEAR) &&
            today.get(Calendar.MONTH) == other.get(Calendar.MONTH)
}

private fun isSameYear(today: Calendar, other: Calendar): Boolean {
    return today.get(Calendar.YEAR) == other.get(Calendar.YEAR)
}