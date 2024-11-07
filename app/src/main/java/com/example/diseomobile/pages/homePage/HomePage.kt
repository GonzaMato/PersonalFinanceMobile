package com.example.diseomobile.pages.homePage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.example.diseomobile.pages.nameSelect.NameSelect
import com.example.diseomobile.ui.theme.SubtitleSemiBold
import com.example.diseomobile.ui.theme.Title2Regular
import com.example.diseomobile.ui.theme.eightyPercentWidth
import com.example.diseomobile.ui.theme.largeDP
import com.example.diseomobile.ui.theme.mediumBorder
import com.example.diseomobile.ui.theme.mediumDP
import com.example.diseomobile.ui.theme.mediumSemiLarge
import com.example.diseomobile.ui.theme.ninetyPercentWidth
import com.example.diseomobile.ui.theme.roundedCorners
import com.example.diseomobile.ui.theme.smallDP
import com.example.diseomobile.ui.theme.thirtyPercentWidth
import com.example.diseomobile.ui.theme.twentyPercentWidth
import com.example.diseomobile.ui.theme.veryLargeDP
import com.example.diseomobile.ui.theme.veryLargePadding
import com.example.diseomobile.ui.theme.xxlDP

@Composable
fun HomePage(navigateToNewTransaction : () -> Unit) {
    val viewmodel = hiltViewModel<ViewModelHomePage>()
    val transaction by viewmodel.transactions.collectAsState(listOf())
    val balance by viewmodel.balance.collectAsState(initial = 0.0)
    val nameProfile by viewmodel.nameProfile.collectAsState(initial = "")
    val context = LocalContext.current


    if (nameProfile.isNullOrEmpty()) {
        NameSelect(context = context, viewmodel = viewmodel)
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
