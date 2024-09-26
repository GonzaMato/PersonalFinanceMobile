package com.example.diseomobile.pages.grapghPage

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.diseomobile.Components.Calendar.CalendarComposable
import com.example.diseomobile.Components.Graph.GraphWeekly
import com.example.diseomobile.Components.RecentActivity.MovementParams
import com.example.diseomobile.Components.RecentActivity.RecentActivityDay
import com.example.diseomobile.pages.homePage.getMovements
import com.example.diseomobile.utils.DayOfWeek
import java.util.Calendar

@Composable
fun GraphPage() {
    val viewModelGraphPage = hiltViewModel<ViewModelGraphPage>()
    val selectedDay by viewModelGraphPage.selectedDate.collectAsState()
    val movements by viewModelGraphPage.movements.collectAsState()

    val isCalendarVisible = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .padding(22.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.45f)
                        .padding(bottom = 18.dp)
                ) {
                    GraphWeekly(
                        movements = getMovements(movements),
                        onDaySelected = {
                            viewModelGraphPage.setSelectedDate(it)
                        },
                        openCalendar = {
                            isCalendarVisible.value = it
                        }
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
                CalendarComposable(
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

@Preview
@Composable
fun PreviewGraphPage() {
    GraphPage()
}