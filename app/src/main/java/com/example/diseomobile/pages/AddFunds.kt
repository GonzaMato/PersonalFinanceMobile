package com.example.diseomobile.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diseomobile.Components.Button.ButtonType
import com.example.diseomobile.Components.Button.FilledButton
import com.example.diseomobile.Components.TextField.TextFieldCustom
import com.example.diseomobile.Components.ViewModel.AddFundsViewModel
import com.example.diseomobile.ui.theme.TitleRegular

@Composable
fun AddFunds(viewModel: AddFundsViewModel = AddFundsViewModel()) {
    val title = viewModel.title.value
    val description = viewModel.description.value
    val amount = viewModel.amount.value
    val date = viewModel.date.value

    Box(modifier = Modifier.fillMaxSize()
        .background(color= Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Add Funds", style = TitleRegular)

            Spacer(modifier = Modifier.height(20.dp))

            TextFieldCustom(
                value = title,
                onValueChange = viewModel::onTitleChange,
                placeHolder = "Title"
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextFieldCustom(
                value = description,
                onValueChange = viewModel::onDescriptionChange,
                placeHolder = "Description"
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextFieldCustom(
                value = amount,
                onValueChange = viewModel::onAmountChange,
                placeHolder = "Amount"
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextFieldCustom(
                value = date,
                onValueChange = viewModel::onDateChange,
                placeHolder = "Date"
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxHeight(0.1f)
                    .fillMaxWidth(0.6f)
            ) {
                FilledButton(
                    "Save",
                    type = ButtonType.PRIMARY,
                    onClick = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAddFunds() {
    AddFunds()
}