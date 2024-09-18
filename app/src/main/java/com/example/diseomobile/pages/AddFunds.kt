package com.example.diseomobile.pages

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.diseomobile.Components.Button.ButtonType
import com.example.diseomobile.Components.Button.FilledButton
import com.example.diseomobile.Components.TextField.TextFieldCustom
import com.example.diseomobile.Components.ViewModel.AddFundsViewModel
import com.example.diseomobile.R
import com.example.diseomobile.ui.theme.BodySemiBold
import com.example.diseomobile.ui.theme.SubtitleRegular
import com.example.diseomobile.ui.theme.SubtitleSemiBold
import com.example.diseomobile.ui.theme.TitleRegular

@Composable
fun AddFunds() {
    val viewModel = hiltViewModel<AddFundsViewModel>()

    val title by viewModel.title.collectAsState()
    val description by viewModel.description.collectAsState()
    val amount by viewModel.amount.collectAsState()
    val date by viewModel.date.collectAsState()


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

            Text(text = stringResource(id = R.string.Date) , style = SubtitleRegular)
            TextFieldCustom(
                value = date,
                onValueChange = viewModel::setDate,
                placeHolder = stringResource(id = R.string.Date)
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