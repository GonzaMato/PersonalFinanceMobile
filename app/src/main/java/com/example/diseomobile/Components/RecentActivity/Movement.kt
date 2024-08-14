package com.example.diseomobile.Components.RecentActivity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diseomobile.ui.theme.BodyRegular
import com.example.diseomobile.ui.theme.PrimaryColor
import com.example.diseomobile.ui.theme.SecondaryColor
import com.example.diseomobile.ui.theme.SubtitleRegular
import com.example.diseomobile.ui.theme.SubtitleSemiBold
import java.util.Date

data class MovementParams(
    val title: String,
    val amount: String,
    val description: String,
    val income: Boolean,
    val date : Date
)

@Composable
fun Movement(movementData : MovementParams) {
    val movementAmountLiteral = movementData.amount
    val movementAmount = if (movementData.income) "$ +$movementAmountLiteral" else "$ -$movementAmountLiteral"
    val movementColor = if (movementData.income) PrimaryColor else SecondaryColor

    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement =  Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column {
            Text(text = movementData.title, color = Color.Black, style = SubtitleSemiBold)
            Text(text = movementData.description, color = Color.Black, style = BodyRegular)
        }
        Text(text = movementAmount, color = movementColor, style = SubtitleRegular)
    }
}


@Preview
@Composable
fun PreviewMovement(){
    Surface(
        color = Color.White,
        modifier = Modifier.width(304.dp)
    ) {
        Column(
        ) {
            Movement(MovementParams("Gasto 1","200", "Le pague a mi tio", false, Date()))

            Movement(MovementParams("Ingreso 1", amount = "300", description = "Me presto plata mi tio", income = true, Date()))
        }
    }
}