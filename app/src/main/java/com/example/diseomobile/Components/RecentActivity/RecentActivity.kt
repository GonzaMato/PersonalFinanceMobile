package com.example.diseomobile.Components.RecentActivity

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diseomobile.Components.Button.ButtonType
import com.example.diseomobile.Components.Button.FilledButton
import com.example.diseomobile.ui.theme.BodyRegular
import com.example.diseomobile.ui.theme.BodySemiBold
import com.example.diseomobile.ui.theme.Primary300
import com.example.diseomobile.ui.theme.Primary400
import com.example.diseomobile.ui.theme.SubtitleSemiBold
import com.example.diseomobile.ui.theme.Title2Regular
import com.example.diseomobile.ui.theme.Title2SemiBold
import com.example.diseomobile.utils.categorizeMovementsByDate
import java.util.Date

@Composable
fun RecentActivity(movements: List<MovementParams>) {
    val categorizedMovements = categorizeMovementsByDate(movements)

    Box(
        modifier = Modifier.border(
            width = 3.dp,
            color = Color.Black,
            shape = RoundedCornerShape(10.dp)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Recent Activity", style = SubtitleSemiBold)

                Box(
                    modifier = Modifier
                        .width(116.dp)
                        .height(40.dp)
                ) {
                    FilledButton(text = "Show All", type = ButtonType.PRIMARY) {}
                }
            }

            Column {
               categorizedMovements.forEach { (date , movement) ->
                   Spacer(modifier = Modifier.height(16.dp))
                   Text(text = date , style = Title2SemiBold, color = Primary400)
                     movement.forEach {
                         Spacer(modifier = Modifier.height(8.dp))
                         Movement(it)
                     }
               }
            }
        }
    }
}


    @Preview
    @Composable
    fun PreviewRecentActivity() {
        Surface(
            modifier = Modifier
                .background(Color.White)
                .width(312.dp)
        ) {


            RecentActivity(
                movements = listOf(
                    MovementParams(
                        "Gasto 1",
                        "200",
                        "Le pague a mi tio",
                        false,
                        Date()
                    ),
                    MovementParams(
                        "Gasto 2",
                        "200",
                        "Le pague a mi tio",
                        false,
                        Date()
                    ),
                    MovementParams(
                        "Gasto 3",
                        "200",
                        "Le pague a mi tio",
                        false,
                        Date(2, 23,30)
                    ),
                )
            )
        }
    }