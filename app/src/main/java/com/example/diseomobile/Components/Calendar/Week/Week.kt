package com.example.diseomobile.Components.Calendar.Week

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diseomobile.ui.theme.BodyRegular
import com.example.diseomobile.ui.theme.PrimaryColor

data class DayParams(
    val day : String,
    val number: Int
)

@Composable
fun WeekCompose(dayParams : List<DayParams>, selected : Boolean = true) {
    val colorBackground = if (selected) PrimaryColor.copy(alpha = 0.5f) else Color.Transparent

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .background(color = colorBackground, shape= RoundedCornerShape(10.dp))
    ) {
        for (day in dayParams){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding( top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
            ) {
                Text(text = day.number.toString(), style = BodyRegular)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = day.day, style = BodyRegular)
            }
        }
    }
}

@Preview
@Composable
fun PreviewWeek() {
    WeekCompose(
        listOf(
            DayParams("Mon", 1),
            DayParams("Tue", 2),
            DayParams("Wed", 3),
            DayParams("Thu", 4),
            DayParams("Fri", 5),
            DayParams("Sat", 6),
            DayParams("Sun", 7),
        )
    )
}