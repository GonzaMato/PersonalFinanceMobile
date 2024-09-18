package com.example.diseomobile.Components.ViewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AddFundsViewModel @Inject constructor() : ViewModel() {
    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    private val _amount = MutableStateFlow("")
    val amount = _amount.asStateFlow()

    private val _date = MutableStateFlow("")
    val date = _date.asStateFlow()

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setDescription(description: String) {
        _description.value = description
    }

    fun setAmount(amount: String) {
        _amount.value = amount
    }

    fun setDate(date: String) {
        _date.value = date
    }

    fun clearFields() {
        _title.value = ""
        _description.value = ""
        _amount.value = ""
        _date.value = ""
    }
}