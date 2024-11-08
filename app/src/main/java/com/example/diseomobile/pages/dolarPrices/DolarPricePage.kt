package com.example.diseomobile.pages.dolarPrices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.diseomobile.Components.Button.ButtonType
import com.example.diseomobile.Components.Button.OutlineButton
import com.example.diseomobile.Components.CurrencyValue.CurrencyValue
import com.example.diseomobile.R
import com.example.diseomobile.api.DolarPrice
import com.example.diseomobile.ui.theme.Primary200
import com.example.diseomobile.ui.theme.Primary300
import com.example.diseomobile.ui.theme.SubtitleRegular
import com.example.diseomobile.ui.theme.TitleSemiBold
import com.example.diseomobile.ui.theme.dolarWidth
import com.example.diseomobile.ui.theme.largeDP
import com.example.diseomobile.ui.theme.mediumDP
import com.example.diseomobile.ui.theme.smallDP
import com.example.diseomobile.ui.theme.xxlDP

@Composable
fun DolarPricePage() {
    val viewModelDolarPrice = hiltViewModel<ViewModelDolarPrice>()
    val dolarPrices by viewModelDolarPrice.dolarPrices.collectAsState()
    val loading by viewModelDolarPrice.loadingPrices.collectAsState()
    val showRetry by viewModelDolarPrice.showRetry.collectAsState()

    if (loading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(xxlDP)
                    .align(Alignment.Center),
                color = Primary200,
                trackColor = Primary300,
            )
        }
    } else if (showRetry) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = CenterHorizontally
            ) {

                Text(
                    text = stringResource(id = R.string.error_loading_prices),
                    style = SubtitleRegular,
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(mediumDP))
                Box(
                    modifier = Modifier
                        .height(xxlDP)
                        .width(dolarWidth)
                ) {
                    OutlineButton(
                        text = stringResource(id = R.string.retry),
                        type = ButtonType.PRIMARY,
                        onClick = {
                            viewModelDolarPrice.retry()
                        }
                    )
                }
            }
        }
    } else {

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Box(modifier = Modifier.padding(mediumDP)) {

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = CenterHorizontally,
                ) {

                    Text(text = stringResource(id = R.string.dolar_price), style = TitleSemiBold)
                    Spacer(modifier = Modifier.height(largeDP))
                    separateDolarInGroups(2, dolarPrices).map {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            it.map {
                                CurrencyValue(it.nombre, it.compra, it.venta, R.drawable.usaflag)
                            }
                        }
                        Spacer(modifier = Modifier.height(smallDP))
                    }
                }
            }
        }
    }
}

fun separateDolarInGroups(amount: Int, dolarPrices: List<DolarPrice>): List<List<DolarPrice>> {
    return dolarPrices.chunked(amount)
}


@Preview
@Composable
fun DolarPricePagePreview() {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        DolarPricePage()
    }
}