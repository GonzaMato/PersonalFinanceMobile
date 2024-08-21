package com.example.diseomobile.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.diseomobile.ui.theme.PrimaryColor
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.diseomobile.R
import com.example.diseomobile.ui.theme.SecondaryColor
import com.example.diseomobile.ui.theme.Title2Regular
import com.example.diseomobile.ui.theme.Title2SemiBold

@Composable
fun BalanceCard(balance: String, negative : Boolean) {
val backgroundColor = if(negative) SecondaryColor else PrimaryColor
val textColor = if(negative) Color.White else Color.Black

    Box(modifier = Modifier
        .background(backgroundColor, RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .padding(16.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Column (
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = stringResource(R.string.Balance), color = textColor, style = Title2SemiBold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = balance, color = textColor, style = Title2Regular)
        }
    }
}

@Preview
@Composable
fun PreviewBalanceCard(){
    BalanceCard(balance = "$ 25.000", negative = false)
}
