package com.example.diseomobile.pages.homePage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.example.diseomobile.Components.BalanceCard
import com.example.diseomobile.Components.Button.ButtonType
import com.example.diseomobile.Components.Button.FilledButton
import com.example.diseomobile.Components.Button.OutlineButton
import com.example.diseomobile.Components.NoRecentActivity.NoRecentActivity
import com.example.diseomobile.Components.RecentActivity.MovementParams
import com.example.diseomobile.Components.RecentActivity.RecentActivity
import com.example.diseomobile.Components.TextField.TextFieldCustom
import com.example.diseomobile.R
import com.example.diseomobile.data.models.transaction.Transaction
import com.example.diseomobile.navigation.WiseRipOffScreens
import com.example.diseomobile.ui.theme.DiseñoMobileTheme
import com.example.diseomobile.ui.theme.SubtitleSemiBold
import com.example.diseomobile.ui.theme.Title2Regular
import com.example.diseomobile.ui.theme.largeDP
import com.example.diseomobile.ui.theme.mediumSemiLarge
import com.example.diseomobile.ui.theme.nientyPercentWidth
import com.example.diseomobile.ui.theme.sixtyPercentWidth
import com.example.diseomobile.ui.theme.smallDP
import com.example.diseomobile.ui.theme.thertyPercentWidth
import com.example.diseomobile.ui.theme.twentyPercentWidth
import com.example.diseomobile.ui.theme.veryLargeDP
import com.example.diseomobile.ui.theme.veryLargePadding
import com.example.diseomobile.ui.theme.xxlDP

@Composable
fun HomePage(navigateToNewTransaction : () -> Unit) {
    val viewmodel = hiltViewModel<ViewModelHomePage>()
    val transaction by viewmodel.transactions.collectAsState(listOf())
    val balance by viewmodel.balance.collectAsState(0.0)
    val nameProfile by viewmodel.nameProfile.collectAsState("")


    if (nameProfile == "") {
        val name = remember { mutableStateOf("") }
        val nameShouldNotBeEmpty = remember {
            mutableStateOf(false)
        }

        Box(modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .shadow(elevation = smallDP)
            .fillMaxHeight(thertyPercentWidth)
            .fillMaxWidth(twentyPercentWidth),
            contentAlignment = Alignment.Center
        ){
            Column(
                verticalArrangement = Arrangement.Center) {
                Text(text = stringResource(id = R.string.createName), style = SubtitleSemiBold)

                Box(modifier = Modifier
                    .fillMaxWidth(nientyPercentWidth),
                    contentAlignment = Alignment.Center
                    ) {
                    TextFieldCustom(
                        value = name.value,
                        onValueChange = { name.value = it },
                        placeHolder = stringResource(id = R.string.EnterName),
                        error = false
                    )
                }
                Spacer(modifier = Modifier.padding(smallDP))
                Box(modifier = Modifier
                    .height(veryLargeDP)
                    .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier =
                        Modifier
                            .fillMaxWidth(sixtyPercentWidth),
                    ) {
                        FilledButton(
                            text = stringResource(id = R.string.accept),
                            type = ButtonType.PRIMARY
                        ) {
                            viewmodel.createProfileIfNonExistant(name.value)
                        }
                    }
                }
            }
        }

    } else {
        val newTransactionButtonType : ButtonType = if (balance < 0.0) ButtonType.SECONDARY else ButtonType.PRIMARY

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .fillMaxHeight()
                .padding(start = veryLargePadding, end = veryLargePadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(mediumSemiLarge))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.WelcomMessage) + " " + nameProfile,
                        style = Title2Regular
                    )
                }
                Spacer(modifier = Modifier.padding(mediumSemiLarge))
                BalanceCard(balance = balance.toString(), negative = balance < 0.0)
                Spacer(modifier = Modifier.padding(mediumSemiLarge))
                Box(
                    modifier = Modifier
                        .height(xxlDP)
                ) {
                    OutlineButton(
                        text = stringResource(id = R.string.AddFunds),
                        type = newTransactionButtonType,
                        onClick = { navigateToNewTransaction() }
                    )
                }
                Spacer(modifier = Modifier.padding(mediumSemiLarge))

                if (transaction.isEmpty()) {
                    NoRecentActivity()
                } else {
                    RecentActivity(
                        movements = getMovements(transaction)
                    )
                }
            }
        }
    }
}


fun getMovements(transactions: List<Transaction>): List<MovementParams> {
    return transactions.map {
        MovementParams(
            title = it.title,
            description = it.description,
            amount = it.amount,
            date = it.date,
            income = it.income
        )
    }
}
