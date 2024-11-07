package com.example.diseomobile.Components.Calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.diseomobile.Components.Button.ButtonType
import com.example.diseomobile.Components.Button.OutlineButton
import com.example.diseomobile.Components.Calendar.Week.WeekCompose
import com.example.diseomobile.R
import com.example.diseomobile.ui.theme.PrimaryColor
import com.example.diseomobile.ui.theme.Title2Regular
import com.example.diseomobile.ui.theme.calendarButtonWidth
import com.example.diseomobile.ui.theme.largePadding
import com.example.diseomobile.ui.theme.mediumBorder
import com.example.diseomobile.ui.theme.mediumDP
import com.example.diseomobile.ui.theme.ninetyPercentWidth
import com.example.diseomobile.ui.theme.roundedCorners
import com.example.diseomobile.ui.theme.smallDP
import com.example.diseomobile.ui.theme.xxlPadding
import com.example.diseomobile.utils.getMonthByDate
import java.util.Date

@Composable
fun MonthSelector(
    viewModel: MonthSelectorViewModel,
    selectedWeekDate: (List<Date>) -> Unit = {},
    closeCalendar: (Boolean) -> Unit = {},
) {
    val lastDayOfCalendar by viewModel.lastDayOfCalendar.collectAsState()
    val calendarWeeks by viewModel.calendarWeeks.collectAsState()
    val selectedWeek by viewModel.selectedWeek.collectAsState()

    Column(
        modifier = Modifier
            .padding(smallDP),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(ninetyPercentWidth)
                .padding(bottom = mediumDP),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(calendarButtonWidth)
                    .height(calendarButtonWidth)

            ) {
                OutlineButton(
                    onClick = { viewModel.updateToPreviousMonth() },
                    type = ButtonType.PRIMARY, text = "<")
            }

            Box(
                modifier = Modifier
                    .background(color = PrimaryColor, shape = RoundedCornerShape(roundedCorners))
                    .border(mediumBorder, Color.Black, shape = RoundedCornerShape(roundedCorners))
                    .padding(start = xxlPadding, end = xxlPadding, top = largePadding, bottom = largePadding)
                    .clickable {
                        closeCalendar(false)
                    }
            ) {
                Text(
                    text = getMonthByDate(lastDayOfCalendar),
                    color = Color.Black,
                    style = Title2Regular
                )
            }

            Box(
                modifier = Modifier
                    .width(calendarButtonWidth)
                    .height(calendarButtonWidth)
            ) {
                OutlineButton(
                    onClick = { viewModel.updateToNextMonth() },
                    type = ButtonType.PRIMARY, text = ">")
            }
        }

        calendarWeeks.forEachIndexed { index, week ->
            WeekCompose(
                dayParams = week,
                selected = selectedWeek == index,
                onClick = {
                    viewModel.setSelectedWeek(index)
                    selectedWeekDate(week.map { it.date })
                }
            )
            Spacer(modifier = Modifier.height(mediumDP))
        }


        Box(modifier = Modifier.height(calendarButtonWidth)) {
            OutlineButton(text = stringResource(id = R.string.close), type = ButtonType.PRIMARY) {
                closeCalendar(false)
            }
        }
    }

}
