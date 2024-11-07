package com.example.diseomobile.Components.RecentActivity

import androidx.compose.foundation.clickable
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class MovementParams(
    val title: String,
    val amount: Double,
    val description: String,
    val income: Boolean,
    val date : Date,
    val id : Int = 0,
    )

@Composable
fun Movement(movementData : MovementParams, navigationToMovement : (Int) -> Unit = {}) {
    val movementAmountLiteral = movementData.amount
    val movementAmount = if (movementData.income) "$ +${roundAmount(movementAmountLiteral)}" else "$ -${roundAmount(movementAmountLiteral)}"
    val movementColor = if (movementData.income) PrimaryColor else SecondaryColor

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigationToMovement(movementData.id) }
        ,
        horizontalArrangement =  Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ){
        Column {
            Text(text = movementData.title, color = MaterialTheme.colorScheme.primary, style = SubtitleSemiBold)
            Text(text = movementData.description, color = MaterialTheme.colorScheme.primary, style = BodyRegular)
            Text(text = formatDate(movementData.date), color = MaterialTheme.colorScheme.primary, style = BodyRegular)
        }
        Text(text = movementAmount, color = movementColor, style = SubtitleRegular)
    }
}

fun roundAmount(amount : Double) : String {
    return when {
        amount < 1000 -> "$$amount"
        amount < 1000000 -> "$${amount.toInt() / 1000}K"
        else -> "$${amount.toInt() / 1000000}M"
    }
}

fun formatDate(date: Date): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return formatter.format(date)
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
            Movement(MovementParams("Gasto 1",200003.0, "Le pague a mi tio", false, Date()))

            Movement(MovementParams("Ingreso 1", amount = 300.0, description = "Me presto plata mi tio", income = true, Date()))
        }
    }
}