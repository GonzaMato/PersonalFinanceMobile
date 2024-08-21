package com.example.diseomobile.Components.Graph

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun ColumnGraph(amount : String, day : String, lengthPercentage : Int, selected : Boolean) {
    val colorOfColumn = if (selected) PrimaryColor else Primary300
    val borderColor = if (selected) Color.Black else Color.Transparent
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = amount , style = BodyRegular, color = colorOfColumn, modifier = Modifier.height(12.dp))
        Box(modifier = Modifier
            .fillMaxHeight()
            .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(10.dp))
            .background(colorOfColumn, shape = RoundedCornerShape(10.dp))){

        }
        Text(text = day, style = BodyRegular, color = colorOfColumn, modifier = Modifier.height(31.dp))
    }
}


@Preview
@Composable
fun PreviewColumnGraph(){
    Box(modifier = Modifier
        .background(Color.White)
        .height(500.dp)) {
        ColumnGraph(amount = "100k", day = "Mon", lengthPercentage = 50, selected = false)
    }
}