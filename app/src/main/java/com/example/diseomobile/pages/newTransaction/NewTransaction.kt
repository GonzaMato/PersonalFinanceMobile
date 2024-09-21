package com.example.diseomobile.pages.newTransaction

import android.app.DatePickerDialog
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.diseomobile.Components.Button.ButtonType
import com.example.diseomobile.Components.Button.FilledButton
import com.example.diseomobile.Components.TextField.TextFieldCustom
import com.example.diseomobile.R
import com.example.diseomobile.ui.theme.SubtitleRegular
import com.example.diseomobile.ui.theme.TitleRegular
import java.util.Calendar
import android.app.TimePickerDialog
import android.widget.CheckBox
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.diseomobile.Components.Button.OutlineButton
import com.example.diseomobile.navigation.WiseRipOffScreens
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun AddFunds(navecontroller: NavHostController) {
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

    // Date Picker Dialog
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
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
            if (calendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                calendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                calendar.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)
            ) {
                if (hourOfDay <= calendar.get(Calendar.HOUR_OF_DAY) &&
                    (hourOfDay < calendar.get(Calendar.HOUR_OF_DAY) || minute <= calendar.get(
                        Calendar.MINUTE
                    ))
                ) {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)
                    viewModel.setDate(calendar.time)
                }
            } else {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                viewModel.setDate(calendar.time)
            }
        },
        calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            ) {
            Text(text = stringResource(id = R.string.AddFunds), style = TitleRegular)

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = stringResource(id = R.string.Title), style = SubtitleRegular)
            TextFieldCustom(
                value = title,
                onValueChange = viewModel::setTitle,
                placeHolder = stringResource(id = R.string.Title),
                error = titleError
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = stringResource(id = R.string.Description), style = SubtitleRegular)
            TextFieldCustom(
                value = description,
                onValueChange = viewModel::setDescription,
                placeHolder = stringResource(id = R.string.Description),
                error = descriptionError
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = stringResource(id = R.string.Amount), style = SubtitleRegular)
            TextFieldCustom(
                value = amount,
                onValueChange = viewModel::setAmount,
                placeHolder = stringResource(id = R.string.Amount),
                error = amountError
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = stringResource(id = R.string.Date), style = SubtitleRegular)
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.2f)
                    .fillMaxWidth(0.8f)
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


            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Text(text = stringResource(id = R.string.Expense), style = SubtitleRegular)
                Spacer(modifier = Modifier.width(8.dp))
                Switch(checked = income, onCheckedChange = {
                    income = it
                }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = stringResource(id = R.string.Income), style = SubtitleRegular)
            }
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.3f)
                        .fillMaxWidth(0.6f)
                ) {
                    FilledButton(
                        "Save",
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
                                navecontroller.navigate(WiseRipOffScreens.Home.name)
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