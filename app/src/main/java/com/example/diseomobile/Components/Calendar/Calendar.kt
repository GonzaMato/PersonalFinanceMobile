package com.example.diseomobile.Components.Calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diseomobile.ui.theme.Tertiary400
import com.example.diseomobile.ui.theme.mediumBorder
import com.example.diseomobile.ui.theme.mediumLargePadding
import com.example.diseomobile.ui.theme.roundedCorners
import com.example.diseomobile.ui.theme.smallBorder
import com.example.diseomobile.ui.theme.smallDP
import java.util.Date

@Composable
fun CalendarComposable(
    monthSelectorViewModel: MonthSelectorViewModel, // Accept ViewModel as a parameter
    selectedWeekDate: (List<Date>) -> Unit = {},
    closeCalendar: (Boolean) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .padding(mediumLargePadding)
            .border(
                width = mediumBorder,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(roundedCorners)
            )
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = RoundedCornerShape(roundedCorners)
            )
    ) {
        // Pass the ViewModel to MonthSelector
        MonthSelector(monthSelectorViewModel, selectedWeekDate, closeCalendar)
    }
}
