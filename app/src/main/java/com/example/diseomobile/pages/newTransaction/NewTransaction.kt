package com.example.diseomobile.pages.newTransaction

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.diseomobile.Components.Button.ButtonType
import com.example.diseomobile.Components.Button.FilledButton
import com.example.diseomobile.Components.Button.OutlineButton
import com.example.diseomobile.Components.TextField.TextFieldCustom
import com.example.diseomobile.R
import com.example.diseomobile.ui.theme.SubtitleRegular
import com.example.diseomobile.ui.theme.TitleRegular
import com.example.diseomobile.ui.theme.eightyPercentWidth
import com.example.diseomobile.ui.theme.largePadding
import com.example.diseomobile.ui.theme.mediumDP
import com.example.diseomobile.ui.theme.sixtyPercentWidth
import com.example.diseomobile.ui.theme.smallDP
import com.example.diseomobile.ui.theme.veryLargePadding
import com.example.diseomobile.ui.theme.xlDP
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun AddFunds(navController: () -> Unit) {
    val viewModel = hiltViewModel<ViewModelNewTransaction>()
    val context = LocalContext.current
    val title by viewModel.title.collectAsState()
    val description by viewModel.description.collectAsState()
    val amount by viewModel.amount.collectAsState()
    val selectedDate by viewModel.date.collectAsState()
    var income by remember {
        mutableStateOf(true)
    }

    val errorToastTitleNotFilled = stringResource(id = R.string.ErrorToastTitleNotFilled)
    val errorToastDescriptionNotFilled = stringResource(id = R.string.ErrorToastTitleNotDescription)
    val errorToastAmountNotFilled = stringResource(id = R.string.ErrorToastAmountNotFilled)
    val errorToastDateNotValid = stringResource(id = R.string.ErrorToastDateNotValid)

    val transactionAdded = stringResource(id = R.string.TransactionAdded)

    val calendar = Calendar.getInstance()

    var titleError by remember {
        mutableStateOf(false)
    }
    var descriptionError by remember {
        mutableStateOf(false)
    }
    var amountError by remember {
        mutableStateOf(false)
    }

    var dateButtonType by remember {
        mutableStateOf(ButtonType.PRIMARY)
    }

    LaunchedEffect(selectedDate) {
        calendar.time = selectedDate
    }

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)


            viewModel.setDate(calendar.time)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    datePickerDialog.datePicker.maxDate = calendar.timeInMillis

// Time Picker Dialog
    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _, hourOfDay, minute ->
            // Update only the time fields (hour, minute)
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, 0)

            // Update the viewModel with the updated time (current date + new time)
            viewModel.setDate(calendar.time)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(largePadding)
                .verticalScroll(rememberScrollState()),

            ) {
            Text(text = stringResource(id = R.string.AddFunds), style = TitleRegular)

            Spacer(modifier = Modifier.height(veryLargePadding))

            Text(text = stringResource(id = R.string.Title), style = SubtitleRegular)
            TextFieldCustom(
                value = title,
                onValueChange = viewModel::setTitle,
                placeHolder = stringResource(id = R.string.Title),
                error = titleError
            )

            Spacer(modifier = Modifier.height(largePadding))

            Text(text = stringResource(id = R.string.Description), style = SubtitleRegular)
            TextFieldCustom(
                value = description,
                onValueChange = viewModel::setDescription,
                placeHolder = stringResource(id = R.string.Description),
                error = descriptionError
            )

            Spacer(modifier = Modifier.height(mediumDP))

            Text(text = stringResource(id = R.string.Amount), style = SubtitleRegular)
            TextFieldCustom(
                value = amount,
                onValueChange = viewModel::setAmount,
                placeHolder = stringResource(id = R.string.Amount),
                error = amountError,
                keyboardType = KeyboardType.Decimal
            )

            Spacer(modifier = Modifier.height(mediumDP))

            Text(text = stringResource(id = R.string.Date), style = SubtitleRegular)
            Box(
                modifier = Modifier
                    .height(xlDP)
                    .fillMaxWidth(eightyPercentWidth)
                    .align(Alignment.CenterHorizontally),
            ) {
                OutlineButton(
                    text = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(
                        selectedDate
                    ),
                    type = dateButtonType,
                    onClick = {
                        datePickerDialog.show()
                        timePickerDialog.show()
                    }
                )
            }


            Spacer(modifier = Modifier.height(mediumDP))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Text(text = stringResource(id = R.string.Expense), style = SubtitleRegular)
                Spacer(modifier = Modifier.width(smallDP))
                Switch(checked = income, onCheckedChange = {
                    income = it
                },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = MaterialTheme.colorScheme.secondary,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = MaterialTheme.colorScheme.secondary
                    )
                )
                Spacer(modifier = Modifier.width(smallDP))
                Text(text = stringResource(id = R.string.Income), style = SubtitleRegular)
            }
            Spacer(modifier = Modifier.height(mediumDP))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier
                        .height(xlDP)
                        .fillMaxWidth(sixtyPercentWidth)
                ) {
                    FilledButton(
                        stringResource(id = R.string.save),
                        type = ButtonType.PRIMARY,
                        onClick = {
                            if (title.isEmpty()) {
                                titleError = true
                                Toast.makeText(
                                    context,
                                    errorToastTitleNotFilled,
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@FilledButton
                            }
                            if (description.isEmpty()) {
                                descriptionError = true
                                Toast.makeText(
                                    context,
                                    errorToastDescriptionNotFilled,
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@FilledButton
                            }

                            if (amount.isEmpty()) {
                                amountError = true
                                Toast.makeText(
                                    context,
                                    errorToastAmountNotFilled,
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@FilledButton
                            }

                            if (selectedDate < calendar.time) {
                                dateButtonType = ButtonType.SECONDARY
                                Toast.makeText(
                                    context,
                                    errorToastDateNotValid,
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@FilledButton
                            }
                            viewModel.clearFields()
                            try {
                                viewModel.addTransaction(
                                    title = title,
                                    description = description,
                                    amount = amount,
                                    date = selectedDate,
                                    income = income,
                                    profileId = 1
                                )
                                Toast.makeText(
                                    context,
                                    transactionAdded,
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController()
                            } catch (e: Exception) {
                                Toast.makeText(
                                    context,
                                    e.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    )
                }
            }
        }
    }
}