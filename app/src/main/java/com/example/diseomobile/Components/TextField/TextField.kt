package com.example.diseomobile.Components.TextField

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diseomobile.ui.theme.BodyRegular

@Composable
fun TextFieldCustom(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String
) {
    BasicTextField(
        value = value, onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(10.dp))
            .border(1.dp , Color.Black, RoundedCornerShape(10.dp))
            .padding(16.dp),
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(text = placeHolder, color = Color.Gray, style = BodyRegular)
            } else {
                innerTextField()
            }
        },
        textStyle = BodyRegular
    )
}


@Preview
@Composable
fun TextFieldPreview() {
    var text by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        TextFieldCustom(value = text, onValueChange = {
            text = it
        }, placeHolder = "Email")
    }
}