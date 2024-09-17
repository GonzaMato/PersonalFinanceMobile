package com.example.diseomobile.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diseomobile.Components.BalanceCard
import com.example.diseomobile.Components.Button.ButtonType
import com.example.diseomobile.Components.Button.OutlineButton
import com.example.diseomobile.Components.RecentActivity.MovementParams
import com.example.diseomobile.Components.RecentActivity.RecentActivity
import com.example.diseomobile.R
import com.example.diseomobile.ui.theme.Title2Regular
import com.example.diseomobile.ui.theme.TitleRegular

@Composable
fun HomePage() {
    Box(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxHeight()
    ) {
        Box(modifier = Modifier
            .padding(start = 24.dp, end = 24.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(12.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.WelcomMessage) + "Gonzalo Mato!",
                        style = Title2Regular
                    )
                }
                Spacer(modifier = Modifier.padding(12.dp))
                BalanceCard(balance = "$ 25.000", negative = false)
                Spacer(modifier = Modifier.padding(12.dp))
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.1f)
                ) {
                    OutlineButton(
                        text = stringResource(id = R.string.AddFunds),
                        type = ButtonType.PRIMARY
                    ) {

                    }
                }
                Spacer(modifier = Modifier.padding(12.dp))
                RecentActivity(
                    movements = listOf(
                        MovementParams(
                            "Gasto 1",
                            1000,
                            "Gasto en comida",
                            false,
                            java.util.Date()
                        ),
                        MovementParams(
                            "Ingreso 1",
                            1000,
                            "Ingreso en trabajo",
                            true,
                            java.util.Date()
                        )
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomePage() {
    HomePage()
}

