package com.example.diseomobile.pages.graphPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.diseomobile.Components.Calendar.CalendarComposable
import com.example.diseomobile.Components.Calendar.MonthSelectorViewModel
import com.example.diseomobile.Components.Graph.GraphWeekly
import com.example.diseomobile.Components.RecentActivity.RecentActivityDay
import com.example.diseomobile.pages.homePage.getMovements
import com.example.diseomobile.ui.theme.fortyfivePercentWidth
import com.example.diseomobile.ui.theme.largePadding
import com.example.diseomobile.ui.theme.veryLargePadding
import com.example.diseomobile.utils.DayOfWeek
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@Composable
fun GraphPage() {
    val viewModelGraphPage = hiltViewModel<ViewModelGraphPage>()
    val monthSelectorViewModel = hiltViewModel<MonthSelectorViewModel>() // Obtain MonthSelectorViewModel

    val selectedDay by viewModelGraphPage.selectedDate.collectAsState()
    val movements by viewModelGraphPage.movements.collectAsState()
    val firstDayOfWeek by viewModelGraphPage.firstWeekDay.collectAsState()
    val lastDayOfWeek by viewModelGraphPage.lastWeekDay.collectAsState()

    val isCalendarVisible = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .padding(veryLargePadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(fortyfivePercentWidth)
                        .padding(bottom = largePadding)
                ) {
                    GraphWeekly(
                        movements = getMovements(movements),
                        onDaySelected = {
                            viewModelGraphPage.setSelectedDate(it)
                        },
                        openCalendar = {
                            isCalendarVisible.value = it
                        },
                        week = formatDateRange(firstDayOfWeek, lastDayOfWeek)
                    )
                }

                val filteredMovements = if (selectedDay != null) {
                    movements.filter { movement ->
                        val calendar = Calendar.getInstance()
                        calendar.time = movement.date
                        val movementDayOfWeek = when (calendar.get(Calendar.DAY_OF_WEEK)) {
                            Calendar.SUNDAY -> DayOfWeek.Sunday
                            Calendar.MONDAY -> DayOfWeek.Monday
                            Calendar.TUESDAY -> DayOfWeek.Tuesday
                            Calendar.WEDNESDAY -> DayOfWeek.Wednesday
                            Calendar.THURSDAY -> DayOfWeek.Thursday
                            Calendar.FRIDAY -> DayOfWeek.Friday
                            Calendar.SATURDAY -> DayOfWeek.Saturday
                            else -> null
                        }
                        movementDayOfWeek == selectedDay
                    }
                } else {
                    listOf()
                }

                RecentActivityDay(getMovements(filteredMovements))
            }
        }

        if (isCalendarVisible.value) {
            // Pass the monthSelectorViewModel to CalendarComposable
            CalendarComposable(
                monthSelectorViewModel = monthSelectorViewModel,
                selectedWeekDate = {
                    viewModelGraphPage.setFirstWeekDay(it.first())
                    viewModelGraphPage.setLastWeekDay(it.last())
                    viewModelGraphPage.loadMovementsOfTheWeek()
                },
                closeCalendar = {
                    isCalendarVisible.value = false
                }
            )
        }
    }
}

fun formatDateRange(date1: Date, date2: Date): String {
    val dateFormat = SimpleDateFormat("d/M") // Formato para día/mes
    val day1Month1 = dateFormat.format(date1)
    val day2Month2 = dateFormat.format(date2)
    return "$day1Month1 - $day2Month2"
}

@Preview
@Composable
fun PreviewGraphPage() {
    GraphPage()
}