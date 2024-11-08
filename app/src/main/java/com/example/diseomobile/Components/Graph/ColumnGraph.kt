package com.example.diseomobile.Components.Graph

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diseomobile.ui.theme.BodyRegular
import com.example.diseomobile.ui.theme.Primary300
import com.example.diseomobile.ui.theme.PrimaryColor
import com.example.diseomobile.ui.theme.graphColumnWidth
import com.example.diseomobile.ui.theme.roundedCorners
import com.example.diseomobile.ui.theme.smallBorder
import com.example.diseomobile.ui.theme.smallDP
import com.example.diseomobile.ui.theme.veryLargeDP
import com.example.diseomobile.ui.theme.verySmallDP
import com.example.diseomobile.ui.theme.xlDP

@Composable
fun ColumnGraph(amount: Int, day: String, lengthPercentage: Int, selected: Boolean, onClick : () -> Unit = {}) {
    val colorOfColumn = if (selected) PrimaryColor else Primary300
    val borderColor = if (selected) Color.Black else Color.Transparent

    Column(
        modifier = Modifier
            .width(graphColumnWidth)
            .fillMaxHeight()
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
    ) {

        Text(
            text = roundAmount(amount),
            style = BodyRegular,
            color = colorOfColumn,
        )
        Spacer(modifier = Modifier.height(smallDP))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(lengthPercentage / 120f)  // Scaled based on the percentage
                .border(
                    width = smallBorder,
                    color = borderColor,
                    shape = RoundedCornerShape(topEnd = roundedCorners, topStart =roundedCorners)
                )
                .background(
                    color = colorOfColumn,
                    shape = RoundedCornerShape(topEnd = roundedCorners, topStart = roundedCorners)
                )
        )

        Spacer(modifier = Modifier.height(verySmallDP))

        Box(modifier = Modifier.height(veryLargeDP)
            .width(xlDP)
            .align(Alignment.CenterHorizontally)) {
            Text(
                text = day,
                style = BodyRegular,
                color = colorOfColumn,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
@Preview
@Composable
fun PreviewColumnGraph() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .height(500.dp)
            .width(200.dp)
    ) {
        ColumnGraph(amount = 1000000, day = "Mon", lengthPercentage = 50, selected = false)
    }
}

fun roundAmount(amount : Int) : String {
    return when {
        amount < 1000 -> "$$amount"
        amount < 1000000 -> "$${amount / 1000}K"
        else -> "$${amount / 1000000}M"
    }
}
