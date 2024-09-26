package com.example.diseomobile.Components.Calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diseomobile.ui.theme.Tertiary400
import java.util.Date

@Composable
fun CalendarComposable(selectedWeekDate : (List<Date>) -> Unit = {}, closeCalendar : (Boolean) -> Unit = {}) {

    Box(modifier = Modifier
        .padding(14.dp)
        .shadow(elevation = 8.dp, shape = RoundedCornerShape(10.dp))
        .background(color = Color.White, shape = RoundedCornerShape(10.dp)))
    {
        MonthSelector(selectedWeekDate, closeCalendar)
    }
}

@Preview
@Composable
fun PreviewCalendarComposable() {
    Box(modifier = Modifier.background(color = Tertiary400)
        .fillMaxHeight()
        .fillMaxWidth()
    ) {
        CalendarComposable()
    }

}
