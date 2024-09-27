package com.example.diseomobile.pages.homePage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.diseomobile.Components.BalanceCard
import com.example.diseomobile.Components.Button.ButtonType
import com.example.diseomobile.Components.Button.OutlineButton
import com.example.diseomobile.Components.RecentActivity.MovementParams
import com.example.diseomobile.Components.RecentActivity.RecentActivity
import com.example.diseomobile.R
import com.example.diseomobile.data.models.transaction.Transaction
import com.example.diseomobile.navigation.WiseRipOffScreens
import com.example.diseomobile.ui.theme.Title2Regular

@Composable
fun HomePage(navecontroller : NavHostController) {
    val viewmodel = hiltViewModel<ViewModelHomePage>()
    val transaction by viewmodel.transactions.collectAsState()
    val balance by viewmodel.balance.collectAsState()

    val newTransactionButtonType : ButtonType = if (balance!! < 0.0) ButtonType.SECONDARY else ButtonType.PRIMARY


    LaunchedEffect(Unit) {
        viewmodel.createProfileIfNonExistant()
        viewmodel.loadProfileBalance(1)
        viewmodel.setTransaction(transaction + viewmodel.getTransactions(1, 10 , 0))
    }

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .verticalScroll(rememberScrollState()) // Habilita el scroll en toda la pantalla
            .fillMaxHeight()
            .padding(start = 24.dp, end = 24.dp)
    ) {
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
                BalanceCard(balance = balance.toString() , negative = balance!! < 0.0)
                Spacer(modifier = Modifier.padding(12.dp))
                Box(
                    modifier = Modifier
                        .height(60.dp)
                ) {
                    OutlineButton(
                        text = stringResource(id = R.string.AddFunds),
                        type = newTransactionButtonType,
                        onClick = { navecontroller.navigate(WiseRipOffScreens.NewTransaction.name)}
                    )
                }
                Spacer(modifier = Modifier.padding(12.dp))
                RecentActivity(
                    movements = getMovements(transaction)
                )
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
