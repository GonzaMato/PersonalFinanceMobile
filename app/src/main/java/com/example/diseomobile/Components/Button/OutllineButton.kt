package com.example.diseomobile.Components.Button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diseomobile.ui.theme.PrimaryColor
import com.example.diseomobile.ui.theme.SecondaryColor
import com.example.diseomobile.ui.theme.SubtitleSemiBold
import com.example.diseomobile.ui.theme.mediumBorder
import com.example.diseomobile.ui.theme.roundedCorners

@Composable
fun OutlineButton(text : String, type : ButtonType, onClick : () -> Unit ) {
    val backgroundColor = when(type){
        ButtonType.PRIMARY -> PrimaryColor
        ButtonType.SECONDARY -> SecondaryColor
    }

    Box(modifier = Modifier
        .border(width = mediumBorder , color = backgroundColor, shape = RoundedCornerShape(roundedCorners))
        .fillMaxWidth()
        .fillMaxHeight()
        .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.Black, style = SubtitleSemiBold)
    }
}

@Preview
@Composable
fun PreviewFOutlineButton(){
    OutlineButton(text = ">", type = ButtonType.PRIMARY, onClick = {})
}