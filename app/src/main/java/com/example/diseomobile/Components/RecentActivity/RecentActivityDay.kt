package com.example.diseomobile.Components.RecentActivity

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.diseomobile.R
import com.example.diseomobile.ui.theme.SubtitleSemiBold
import com.example.diseomobile.ui.theme.Title2SemiBold
import com.example.diseomobile.ui.theme.largeBorder
import com.example.diseomobile.ui.theme.largePadding
import com.example.diseomobile.ui.theme.mediumDP
import com.example.diseomobile.ui.theme.roundedCorners
import java.util.Calendar
import java.util.Date

@Composable
fun RecentActivityDay(recentMovement : List<MovementParams>, navigationToMovement : (Int) -> Unit) {

    Box(
        modifier = Modifier.border(
            width = largeBorder,
            color = MaterialTheme.colorScheme.primary, //Arpo
            shape = RoundedCornerShape(roundedCorners)
        )
    ) {
        Column(
            modifier = Modifier.padding(largePadding)
        ) {

            if (recentMovement.isEmpty()) {
                Text(
                    text = stringResource(id = R.string.no_movements),
                    style = SubtitleSemiBold,
                    color = MaterialTheme.colorScheme.secondary
                )
            } else {
                LazyColumn {
                    item {
                        Text(
                            text = getDateName(date = recentMovement[0].date),
                            style = Title2SemiBold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(mediumDP))
                        recentMovement.forEach { it ->
                            Spacer(modifier = Modifier.height(mediumDP))
                            Movement(movementData = it, navigationToMovement)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun getDateName(date: Date): String {
    val calendarDate = Calendar.getInstance()
    calendarDate.time = date

    val dayOfWeek = calendarDate.get(Calendar.DAY_OF_WEEK)

    val dayOfWeekStringRes = when (dayOfWeek) {
        Calendar.SUNDAY -> R.string.sunday
        Calendar.MONDAY -> R.string.monday
        Calendar.TUESDAY -> R.string.tuesday
        Calendar.WEDNESDAY -> R.string.wednesday
        Calendar.THURSDAY -> R.string.thursday
        Calendar.FRIDAY -> R.string.friday
        Calendar.SATURDAY -> R.string.saturday
        else -> null
    }

    return dayOfWeekStringRes?.let { stringResource(it) } ?: ""
}