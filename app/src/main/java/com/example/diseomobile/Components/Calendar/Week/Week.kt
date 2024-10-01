package com.example.diseomobile.Components.Calendar.Week

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.diseomobile.ui.theme.Tertiary400
import com.example.diseomobile.ui.theme.roundedCorners
import com.example.diseomobile.ui.theme.smallDP
import com.example.diseomobile.ui.theme.verySmallDP
import java.util.Date

data class DayParams(
    val day : String,
    val number: Int,
    val inTheMonth : Boolean = true,
    val date : Date = Date()
)

@Composable
fun WeekCompose(dayParams : List<DayParams>, selected : Boolean = true, onClick : () -> Unit = {}) {
    val colorBackground = if (selected) PrimaryColor.copy(alpha = 0.5f) else Color.Transparent


    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .background(color = colorBackground, shape= RoundedCornerShape(roundedCorners))
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        for (day in dayParams){
            val textColor = if (day.inTheMonth || selected) Color.Black else Tertiary400
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding( top = smallDP, bottom = smallDP, start = smallDP, end = smallDP)
            ) {
                Text(text = day.number.toString(), style = BodyRegular, color = textColor)
                Spacer(modifier = Modifier.height(verySmallDP))
                Text(text = day.day, style = BodyRegular, color = textColor)
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