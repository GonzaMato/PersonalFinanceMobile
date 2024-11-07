package com.example.diseomobile.Components.CurrencyValue

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diseomobile.R
import com.example.diseomobile.ui.theme.BodySemiBold
import com.example.diseomobile.ui.theme.currencyImageSize
import com.example.diseomobile.ui.theme.mediumBorder
import com.example.diseomobile.ui.theme.roundedCorners
import com.example.diseomobile.ui.theme.smallDP

@Composable
fun CurrencyValue(name : String , priceBuy : Double, priceSell : Double, imageRes : Int) {

    Box(
        modifier = Modifier.border(
            BorderStroke(mediumBorder, MaterialTheme.colorScheme.primary),
            shape = RoundedCornerShape(roundedCorners)
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(smallDP)
                .width(150.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(currencyImageSize)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(smallDP))

            Text(
                text = name,
                style = BodySemiBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.sellValue) + " $$priceSell",
                style = BodySemiBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = stringResource(id = R.string.buyValue) +" $$priceBuy",
                style = BodySemiBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}


@Preview
@Composable
fun CurrencyValuePreview() {
    Box(modifier = Modifier.background(color = Color.White)) {
        CurrencyValue("Dolar Tarjeta", 100.0, 30.0, R.drawable.usaflag)
    }
}