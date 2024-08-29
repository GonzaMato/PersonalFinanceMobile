package com.example.diseomobile.Components.Graph

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diseomobile.Components.RecentActivity.MovementParams
import com.example.diseomobile.ui.theme.PrimaryColor
import com.example.diseomobile.ui.theme.Title2Regular
import com.example.diseomobile.ui.theme.TitleRegular
import com.example.diseomobile.utils.SeparateByWeek
import com.example.diseomobile.utils.getLengthForWeekDays
import java.util.Calendar

@Composable
fun GraphWeekly(movements: List<MovementParams>) {
    val weeklyMovement = SeparateByWeek(movements)
    val scaledWeek = getLengthForWeekDays(weeklyMovement)
    val selected = remember { mutableStateOf(-1) }  // Index of the selected column

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
                    .background(color = PrimaryColor, shape = RoundedCornerShape(50.dp))
                    .padding(start = 60.dp, end = 60.dp)
            ) {
                Text(text = "Weekly", color = Color.Black, style = Title2Regular)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                scaledWeek.entries.forEachIndexed { index, (day, length) ->
                    weeklyMovement[day]?.let {
                        ColumnGraph(
                            amount = it,
                            day = day.name.take(3),
                            lengthPercentage = length,
                            selected = selected.value == index,
                            onClick = {
                                selected.value = index
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
                MovementParams("title", 25000, "description", true, twoDaysAgo.time),
                MovementParams("title", 2000, "description", true, twoDaysAgo.time),
                MovementParams("title", 0, "description", true, yesterday.time),
                MovementParams("title", 1000, "description", true, java.util.Date()),
                MovementParams("title", 3000, "description", true, java.util.Date()),
                MovementParams("title", 500000, "description", true, java.util.Date()),
                MovementParams("title", 800000000, "description", true, java.util.Date()),
                MovementParams("title", 70, "description", true, java.util.Date()),
                MovementParams("title", 1, "description", true, java.util.Date())
            )
        )
    }
}
