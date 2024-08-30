package com.example.diseomobile.Components.Calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diseomobile.Components.Button.ButtonType
import com.example.diseomobile.Components.Button.OutlineButton
import com.example.diseomobile.ui.theme.PrimaryColor
import com.example.diseomobile.ui.theme.Title2Regular

@Composable
fun MonthSelector(month : String) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .width(48.dp)
            .height(48.dp)) {
            OutlineButton(onClick = { /*TODO*/ }, type = ButtonType.PRIMARY, text = "<")
        }

        Box(
            modifier = Modifier
                .background(color = PrimaryColor, shape = RoundedCornerShape(10.dp))
                .padding(start = 60.dp, end = 60.dp, top = 20.dp, bottom = 20.dp)
        ) {
            Text(text = month, color = Color.Black, style = Title2Regular)
        }

        Box(modifier = Modifier
            .width(48.dp)
            .height(48.dp)) {
            OutlineButton(onClick = { /*TODO*/ }, type = ButtonType.PRIMARY, text = ">")
        }
    }
}


@Preview
@Composable
fun PreviewMonthSelector() {
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .background(color = Color.White)) {
        MonthSelector("May")
    }
}