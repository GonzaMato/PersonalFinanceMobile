package com.example.diseomobile.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diseomobile.Components.Graph.GraphWeekly
import com.example.diseomobile.Components.RecentActivity.MovementParams
import com.example.diseomobile.Components.RecentActivity.RecentActivity
import com.example.diseomobile.Components.RecentActivity.RecentActivityDay
import com.example.diseomobile.utils.DayOfWeek
import java.util.Calendar
import kotlin.io.path.fileVisitor

@Composable
fun GraphPage() {
    val selectedDay = remember { mutableStateOf<DayOfWeek?>(null) }

    val movements = listOf(
        MovementParams("2021-10-01", 100, "Gasto en comida", false, java.util.Date()),
        MovementParams("2021-10-02", 20, "Ingreso en trabajo", true, java.util.Date()),
        MovementParams("2021-10-03", 50, "Gasto en comida", false, java.util.Date()),
        MovementParams("2021-10-04", 100, "Gasto en comida", false, java.util.Date()),
        MovementParams("2021-10-05", 20, "Ingreso en trabajo", true, java.util.Date()),
        MovementParams("2021-10-06", 50, "Gasto en comida", false, java.util.Date()),
        MovementParams("2021-10-07", 100, "Gasto en comida", false, java.util.Date()),
        MovementParams("2021-10-08", 20, "Ingreso en trabajo", true, java.util.Date()),
        MovementParams("2021-10-09", 50, "Gasto en comida", false, java.util.Date()),
        MovementParams("2021-10-10", 100, "Gasto en comida", false, java.util.Date()),
    )
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
                        movements = movements,
                        onDaySelected = {
                            selectedDay.value = it
                        }
                    )
                }
                val filteredMovements = if (selectedDay.value != null) {
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
                        movementDayOfWeek == selectedDay.value
                    }
                } else {
                    movements
                }

                RecentActivityDay(filteredMovements)
                }
            }
    }
}

@Preview
@Composable
fun PreviewGraphPage() {
    GraphPage()
}