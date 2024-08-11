package com.example.diseomobile.Button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.diseomobile.ui.theme.PrimaryColor
import com.example.diseomobile.ui.theme.Secondary200

@Composable
fun ButtonMain(color : Color, buttonText: String){
    Surface(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { /*TODO*/ }, colors = ButtonColors(color, Color.White , color , color)) {
            Text(text = buttonText)
        }
    }
}


@Preview
@Composable
fun PreviewButton(){
    ButtonMain(color = PrimaryColor, buttonText = "Soy un boton")
}