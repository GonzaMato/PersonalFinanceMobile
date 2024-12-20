package com.example.diseomobile.Components.Graph

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diseomobile.Components.RecentActivity.MovementParams
import com.example.diseomobile.R
import com.example.diseomobile.ui.theme.PrimaryColor
import com.example.diseomobile.ui.theme.Title2Regular
import com.example.diseomobile.ui.theme.mediumBorder
import com.example.diseomobile.ui.theme.mediumDP
import com.example.diseomobile.ui.theme.veryRoundedCorners
import com.example.diseomobile.ui.theme.xxxlPadding
import com.example.diseomobile.utils.DayOfWeek
import com.example.diseomobile.utils.SeparateByWeek
import com.example.diseomobile.utils.getLengthForWeekDays
import com.example.diseomobile.utils.orderWeekEntriesByFirstDay
import java.util.Calendar
import java.util.Date

@Composable
fun GraphWeekly(movements: List<MovementParams>, onDaySelected: (DayOfWeek) -> Unit = {}, openCalendar: (Boolean) -> Unit = {}, week : String, firstDay : Date) {
    val weeklyMovement = SeparateByWeek(movements)
    val scaledWeek = getLengthForWeekDays(weeklyMovement)
    val selected = remember { mutableStateOf(-1) }  // Index of the selected column
    val orderedWeekEntries = orderWeekEntriesByFirstDay(scaledWeek, firstDay)

    val weekText = if (week == "") {
        stringResource(id = R.string.selectWeek)
    } else {
        week
    }

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Box(
                modifier = Modifier
                    .background(color = PrimaryColor, shape = RoundedCornerShape(veryRoundedCorners))
                    .border(mediumBorder, Color.Black, shape = RoundedCornerShape(veryRoundedCorners))
                    .padding(start =xxxlPadding, end =xxxlPadding)
                    .clickable {
                        openCalendar(true)
                    }
            ) {
                Text(text = weekText, color = Color.Black, style = Title2Regular)
            }

            Spacer(modifier = Modifier.height(mediumDP))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                orderedWeekEntries.forEachIndexed { index, (day, length) ->
                    weeklyMovement[day]?.let {
                        ColumnGraph(
                            amount = it,
                            day = day.name.take(3),
                            lengthPercentage = length,
                            selected = selected.value == index,
                            onClick = {
                                selected.value = index
                                onDaySelected(day)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewGraph() {
    val twoDaysAgo = Calendar.getInstance()
    twoDaysAgo.add(Calendar.DAY_OF_MONTH, -2)
    val yesterday = Calendar.getInstance()
    yesterday.add(Calendar.DAY_OF_MONTH, -1)

    Box(modifier = Modifier
        .height(300.dp)
        .background(color = Color.White)) {
        GraphWeekly(
            movements = listOf(
                MovementParams("title", 25000.0, "description", true, twoDaysAgo.time),
                MovementParams("title", 2000.0, "description", true, twoDaysAgo.time),
                MovementParams("title", 0.0, "description", true, yesterday.time),
                MovementParams("title", 1000.0, "description", true, java.util.Date()),
                MovementParams("title", 3000.0, "description", true, java.util.Date()),
                MovementParams("title", 500000.0, "description", true, java.util.Date()),
                MovementParams("title", 800000000.0, "description", true, java.util.Date()),
                MovementParams("title", 70.0, "description", true, java.util.Date()),
                MovementParams("title", 1.0, "description", true, java.util.Date())
            ),
            week = "Week 1",
            firstDay = Date()
        )
    }
}
