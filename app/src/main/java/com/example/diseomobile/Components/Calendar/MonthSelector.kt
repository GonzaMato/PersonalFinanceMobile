package com.example.diseomobile.Components.Calendar

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.formatWithSkeleton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diseomobile.Components.Button.ButtonType
import com.example.diseomobile.Components.Button.OutlineButton
import com.example.diseomobile.Components.Calendar.Week.DayParams
import com.example.diseomobile.Components.Calendar.Week.WeekCompose
import com.example.diseomobile.ui.theme.PrimaryColor
import com.example.diseomobile.ui.theme.Title2Regular
import com.example.diseomobile.utils.getLastDayOfNextMonth
import com.example.diseomobile.utils.getLastDayOfPreviousMonth
import com.example.diseomobile.utils.getLastDayOfThisMonth
import com.example.diseomobile.utils.getMonthByDate
import com.example.diseomobile.utils.getWeeksForMonth
import java.util.Date

@Composable
fun MonthSelector(selectedWeekDate : (List<Date>) -> Unit = {}, closeCalendar : (Boolean) -> Unit = {}) {
    val amountOfWeeks = 5
    val lastDayOfCalendar: MutableState<Date> = remember {
        mutableStateOf(getLastDayOfThisMonth(Date()) )
    }
    val calendarWeeks: MutableState<List<List<DayParams>>> = remember {
        mutableStateOf(getWeeksForMonth(lastDayOfCalendar.value, amountOfWeeks))
    }
    val selectedWeek = remember {
        mutableIntStateOf(amountOfWeeks - 1)
    }

    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
            ) {
                OutlineButton(onClick = {
                    lastDayOfCalendar.value = getLastDayOfPreviousMonth(lastDayOfCalendar.value)
                    calendarWeeks.value = getWeeksForMonth(lastDayOfCalendar.value, amountOfWeeks)
                }, type = ButtonType.PRIMARY, text = "<")
            }

            Box(
                modifier = Modifier
                    .background(color = PrimaryColor, shape = RoundedCornerShape(10.dp))
                    .padding(start = 60.dp, end = 60.dp, top = 20.dp, bottom = 20.dp)
                    .clickable {
                        closeCalendar(false)
                    }
            ) {
                Text(
                    text = getMonthByDate(lastDayOfCalendar.value),
                    color = Color.Black,
                    style = Title2Regular
                )
            }

            Box(
                modifier = Modifier
                    .width(48.dp)
                    .height(48.dp)
            ) {
                OutlineButton(onClick = {
                    lastDayOfCalendar.value = getLastDayOfNextMonth(lastDayOfCalendar.value)
                    calendarWeeks.value = getWeeksForMonth(lastDayOfCalendar.value, amountOfWeeks)
                }, type = ButtonType.PRIMARY, text = ">")
            }
        }

        for (i in calendarWeeks.value.indices) {
            WeekCompose(
                dayParams = calendarWeeks.value[i],
                selected = selectedWeek.intValue == i,
                onClick = {
                    selectedWeek.intValue = i
                    selectedWeekDate(calendarWeeks.value[i].map { it.date })
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}


@Preview
@Composable
fun PreviewMonthSelector() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        MonthSelector()
    }
}