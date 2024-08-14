package com.example.diseomobile.utils

import com.example.diseomobile.Components.RecentActivity.MovementParams
import java.util.*
import java.text.SimpleDateFormat

fun categorizeMovementsByDate(movements: List<MovementParams>): Map<String, List<MovementParams>> {
    val today = Calendar.getInstance()

    val categorizedMovements = movements
        .sortedByDescending { it.date }
        .groupBy { movement ->
            val movementDate = Calendar.getInstance().apply { time = movement.date }

            when {
                isSameDay(today, movementDate) -> "Today"
                isLastWeek(movementDate) -> "Last Week"
                isSameMonth(today, movementDate) -> "This Month"
                isLastMonth(movementDate) -> "Last Month"
                isSameYear(today, movementDate) -> "This Year"
                else -> SimpleDateFormat("yyyy").format(movement.date)
            }
        }

    return categorizedMovements
}

private fun isSameDay(today: Calendar, other: Calendar): Boolean {
    return today.get(Calendar.YEAR) == other.get(Calendar.YEAR) &&
            today.get(Calendar.DAY_OF_YEAR) == other.get(Calendar.DAY_OF_YEAR)
}

private fun isLastWeek(other: Calendar): Boolean {
    val today = Calendar.getInstance()
    today.add(Calendar.DAY_OF_YEAR, -7)
    return !isSameDay(today, other) && other.after(today)
}

private fun isSameMonth(today: Calendar, other: Calendar): Boolean {
    return today.get(Calendar.YEAR) == other.get(Calendar.YEAR) &&
            today.get(Calendar.MONTH) == other.get(Calendar.MONTH)
}

private fun isLastMonth(other: Calendar): Boolean {
    val today = Calendar.getInstance()
    today.add(Calendar.MONTH, -1)
    return today.get(Calendar.YEAR) == other.get(Calendar.YEAR) &&
            today.get(Calendar.MONTH) == other.get(Calendar.MONTH)
}

private fun isSameYear(today: Calendar, other: Calendar): Boolean {
    return today.get(Calendar.YEAR) == other.get(Calendar.YEAR)
}
