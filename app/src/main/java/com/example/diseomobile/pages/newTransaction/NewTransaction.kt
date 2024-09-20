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
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.*
@Composable
fun AddFunds() {
    val viewModel = hiltViewModel<ViewModelNewTransaction>()

    val title by viewModel.title.collectAsState()
    val description by viewModel.description.collectAsState()
    val amount by viewModel.amount.collectAsState()
    val selectedDate by viewModel.date.collectAsState()

    val calendar = Calendar.getInstance()

    // Inicializamos el calendar con el valor de selectedDate
    LaunchedEffect(selectedDate) {
        calendar.time = selectedDate
    }

    // Date Picker Dialog
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
        },
        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
    )

    // Time Picker Dialog
    val timePickerDialog = TimePickerDialog(
        LocalContext.current,
        { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            viewModel.setDate(calendar.time) // Guardar el valor en el ViewModel como Date
        },
        calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true
    )


    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

        ) {
            Text(text = stringResource(id = R.string.AddFunds), style = TitleRegular)

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = stringResource(id = R.string.Title) , style = SubtitleRegular)
            TextFieldCustom(
                value = title,
                onValueChange = viewModel::setTitle,
                placeHolder = stringResource(id = R.string.Title)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = stringResource(id = R.string.Description) , style = SubtitleRegular)
            TextFieldCustom(
                value = description,
                onValueChange = viewModel::setDescription,
                placeHolder = stringResource(id = R.string.Description)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = stringResource(id = R.string.Amount) , style = SubtitleRegular)
            TextFieldCustom(
                value = amount,
                onValueChange = viewModel::setAmount,
                placeHolder = stringResource(id = R.string.Amount)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = stringResource(id = R.string.Date), style = SubtitleRegular)
            FilledButton(
                text = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(selectedDate),
                type = ButtonType.SECONDARY,
                onClick = {
                    datePickerDialog.show() // Mostrar el selector de fecha
                    timePickerDialog.show()  // Mostrar el selector de hora después de la fecha
                }
            )


            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){

                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.2f)
                        .fillMaxWidth(0.6f)
                ) {
                    FilledButton(
                        "Save",
                        type = ButtonType.PRIMARY,
                        onClick = {
                            viewModel.clearFields()
                            viewModel.addTransaction(
                                title = title,
                                description = description,
                                amount = amount,
                                date = selectedDate,
                                income = true
                            )
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewAddFunds() {
    AddFunds()
}