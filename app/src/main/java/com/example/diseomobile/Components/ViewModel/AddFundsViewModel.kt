package com.example.diseomobile.Components.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AddFundsViewModel : ViewModel() {
    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val amount = mutableStateOf("")
    val date = mutableStateOf("")

    fun onTitleChange(newTitle: String) {
        title.value = newTitle
    }

    fun onDescriptionChange(newDescription: String) {
        description.value = newDescription
    }

    fun onAmountChange(newAmount: String) {
        amount.value = newAmount
    }

    fun onDateChange(newDate: String) {
        date.value = newDate
    }

}