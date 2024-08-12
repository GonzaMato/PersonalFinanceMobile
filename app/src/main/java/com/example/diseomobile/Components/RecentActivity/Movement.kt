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
import com.example.diseomobile.ui.theme.SecondaryColor

@Composable
fun Movement() {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        horizontalArrangement =  Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column {
            Text(text = "Gasto", color = Color.Black, style = MaterialTheme.typography.titleSmall)
            Text(text = "Almuerzo", color = Color.Black, style = MaterialTheme.typography.bodySmall)
        }
        Text(text = "- $ 25.000", color = SecondaryColor, style = MaterialTheme.typography.bodyMedium)
    }
}


@Preview
@Composable
fun PreviewMovement(){
    Surface(
        color = Color.White,
        modifier = Modifier.width(304.dp)
    ) {
        Movement()
    }
}