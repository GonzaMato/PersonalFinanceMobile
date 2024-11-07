package com.example.diseomobile.Components.RecentActivity

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.diseomobile.R
import com.example.diseomobile.ui.theme.SubtitleSemiBold
import com.example.diseomobile.ui.theme.Title2SemiBold
import com.example.diseomobile.ui.theme.largeBorder
import com.example.diseomobile.ui.theme.largePadding
import com.example.diseomobile.ui.theme.mediumDP
import com.example.diseomobile.ui.theme.roundedCorners
import com.example.diseomobile.ui.theme.smallDP
import com.example.diseomobile.utils.categorizeMovementsByDate

@Composable
fun RecentActivity(movements: List<MovementParams>, navigationToMovement : (Int) -> Unit) {
    val nameByDate = mapOf(
        "Today" to stringResource(R.string.Today),
        "ThisWeek" to stringResource(R.string.ThisWeek),
        "LastWeek" to stringResource(R.string.LastWeek),
        "ThisMonth" to stringResource(R.string.ThisMonth),
        "LastMonth" to stringResource(R.string.LastMonth),
        "ThisYear" to stringResource(R.string.ThisYear),
    )
    val categorizedMovements = categorizeMovementsByDate(nameByDate, movements)

    Box(
        modifier = Modifier.border(
            width = largeBorder,
            color = MaterialTheme.colorScheme.primary, //Aproposito para que en en blackTheme no se vea
            shape = RoundedCornerShape(roundedCorners)
        )
    ) {
        Column(
            modifier = Modifier.padding(largePadding)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(R.string.RecentActivity), style = SubtitleSemiBold)

            }

            Column {
               categorizedMovements.forEach { (date , movement) ->
                   Spacer(modifier = Modifier.height(mediumDP))
                   Text(text = date , style = Title2SemiBold, color = MaterialTheme.colorScheme.secondary)
                     movement.forEach {
                         Spacer(modifier = Modifier.height(smallDP))
                         Movement(it, navigationToMovement)
                     }
               }
            }
        }
    }
}