package com.example.diseomobile.Components.TextField

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.diseomobile.ui.theme.BodyRegular
import com.example.diseomobile.ui.theme.SecondaryColor
import com.example.diseomobile.ui.theme.largePadding
import com.example.diseomobile.ui.theme.roundedCorners
import com.example.diseomobile.ui.theme.smallBorder
import com.example.diseomobile.ui.theme.smallPadding

@Composable
fun TextFieldCustom(
    value: String,
    onValueChange: (String) -> Unit,
    placeHolder: String,
    error: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    val borderColor = if (error) SecondaryColor else Color.Black

    BasicTextField(
        value = value,
        onValueChange = { onValueChange(it)},
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray.copy(alpha = 0.1f), RoundedCornerShape(roundedCorners))
            .border(smallBorder, borderColor, RoundedCornerShape(roundedCorners))
            .padding(largePadding),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        decorationBox = { innerTextField ->
            Box {
                innerTextField()
                if (value.isEmpty()) {
                    Text(
                        text = placeHolder,
                        color = Color.Gray,
                        style = BodyRegular,
                        modifier = Modifier.padding(start =smallPadding, top = smallPadding)
                    )
                }
            }
        },
        textStyle = BodyRegular.copy(color = MaterialTheme.colorScheme.primary)
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
        }, placeHolder = "Email",
            error = false
        )
    }
}