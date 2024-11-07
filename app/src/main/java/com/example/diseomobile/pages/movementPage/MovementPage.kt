package com.example.diseomobile.pages.movementPage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.diseomobile.Components.Button.ButtonType
import com.example.diseomobile.Components.Button.FilledButton
import com.example.diseomobile.Components.RecentActivity.MovementParams
import com.example.diseomobile.R
import com.example.diseomobile.data.models.transaction.Transaction
import com.example.diseomobile.ui.theme.BodyRegular
import com.example.diseomobile.ui.theme.Primary200
import com.example.diseomobile.ui.theme.Primary300
import com.example.diseomobile.ui.theme.PrimaryColor
import com.example.diseomobile.ui.theme.SecondaryColor
import com.example.diseomobile.ui.theme.SubtitleRegular
import com.example.diseomobile.ui.theme.SubtitleSemiBold
import com.example.diseomobile.ui.theme.largeDP
import com.example.diseomobile.ui.theme.mediumBorder
import com.example.diseomobile.ui.theme.mediumDP
import com.example.diseomobile.ui.theme.smallDP
import com.example.diseomobile.ui.theme.xxlDP
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovementPage(navController: () -> Unit, movementId : Int) {
    val viewModel = hiltViewModel<ViewModelMovement>()
    val context = LocalContext.current
    val errorDeletingTransaction = stringResource(id = R.string.ErrorDeletingTransaction)

    val transaction = viewModel.getTransactionById(movementId).observeAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopAppBar(
            title = {
                Text(
                    stringResource(id = R.string.Details),
                    color = MaterialTheme.colorScheme.primary,
                    style = SubtitleSemiBold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = stringResource(id = R.string.goBack),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(largeDP) // Smaller icon size
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        )

        // Divider to separate the TopAppBar from the content
        Divider(color = Color.Gray, thickness = mediumBorder)

        transaction.value?.let { txn ->

            val movementParams = getMovement(txn)

            // Movement Details
            Column(
                modifier = Modifier
                    .padding(PaddingValues(mediumDP))
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(mediumDP)
            ) {
                // Title
                Text(
                    text = stringResource(id = R.string.Title),
                    style = SubtitleSemiBold,
                    color = Color.Gray
                )
                Text(
                    text = movementParams.title,
                    style = SubtitleRegular,
                )
                Spacer(modifier = Modifier.padding(smallDP))

                Text(
                    text = stringResource(id = R.string.Description),
                    style = SubtitleSemiBold,
                    color = Color.Gray
                )
                Text(
                    text = movementParams.description,
                    style = BodyRegular
                )

                Spacer(modifier = Modifier.padding(smallDP))

                Text(
                    text = stringResource(id = R.string.Date),
                    style = SubtitleSemiBold,
                    color = Color.Gray
                )
                Text(
                    text = formatDate(movementParams.date),
                    style = SubtitleRegular
                )

                Spacer(modifier = Modifier.padding(smallDP))

                // Debit
                Text(
                    text = stringResource(id = R.string.Amount),
                    style = SubtitleSemiBold,
                    color = Color.Gray
                )
                Text(
                    text = if (!movementParams.income) "-$${formatAmount(movementParams.amount)}" else "+$${
                        formatAmount(
                            movementParams.amount
                        )
                    }",
                    style = SubtitleRegular,
                    color = if (!movementParams.income) SecondaryColor else PrimaryColor
                )

                Spacer(modifier = Modifier.padding(smallDP))

                Box(
                    modifier = Modifier
                        .height(xxlDP)
                        .fillMaxWidth(),
                ) {
                    FilledButton(
                        text = stringResource(id = R.string.DeleteMovement),
                        type = ButtonType.SECONDARY
                    ) {
                        try {
                            viewModel.deleteTransaction(movementParams.id)
                            navController()
                        } catch (e: Exception) {
                            Toast.makeText(context, errorDeletingTransaction, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        } ?: run {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(xxlDP)
                        .align(Alignment.Center),
                    color = Primary200,
                    trackColor = Primary300,
                )
            }
        }
    }
}

// Helper function to format the date
fun formatDate(date: Date): String {
    val formatter = SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault()) // Updated pattern
    return formatter.format(date)
}

// Helper function to format the amount
fun formatAmount(amount: Double): String {
    return String.format("%,.2f", amount)
}


fun getMovement(transaction: Transaction): MovementParams {
    return MovementParams(
            title = transaction.title,
            description = transaction.description,
            amount = transaction.amount,
            date = transaction.date,
            income = transaction.income,
            id = transaction.id
        )
}


