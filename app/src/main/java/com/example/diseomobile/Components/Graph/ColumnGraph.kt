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

@Composable
fun ColumnGraph(amount: Int, day: String, lengthPercentage: Int, selected: Boolean, onClick : () -> Unit = {}) {
    val colorOfColumn = if (selected) PrimaryColor else Primary300
    val borderColor = if (selected) Color.Black else Color.Transparent

    Column(
        modifier = Modifier
            .width(45.dp)
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
        Spacer(modifier = Modifier.height(8.dp))

        // Box for the dynamic height of the bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(lengthPercentage / 120f)  // Scaled based on the percentage
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
                )
                .background(
                    color = colorOfColumn,
                    shape = RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)
                )
        )

        Spacer(modifier = Modifier.height(4.dp))

        // Amount text on top of the bar
        Box(modifier = Modifier.height(31.dp)
            .width(40.dp)
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
