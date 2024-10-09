package com.example.diseomobile.pages.newTransaction

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diseomobile.data.WiseRipOffDatabase
import com.example.diseomobile.data.models.transaction.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ViewModelNewTransaction @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val wiseRipOffDatabase = WiseRipOffDatabase.getDataBase(context)

    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _description = MutableStateFlow("")
    val description = _description.asStateFlow()

    private val _amount = MutableStateFlow("")
    val amount = _amount.asStateFlow()

    private val _date = MutableStateFlow(Date())
    val date: StateFlow<Date> = _date

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setDescription(description: String) {
        _description.value = description
    }

    @SuppressLint("DefaultLocale")
    fun setAmount(amount: String) {
        if (amount == ""){
            _amount.value = ""
            return
        }
        val newAmount = amount.toFloatOrNull()
        if (newAmount != null && newAmount >= 0) {
            val formattedAmount = String.format("%.2f", newAmount)
            _amount.value = formattedAmount
        }
    }

    fun setDate(date: Date) {
        _date.value = date
    }

    fun clearFields() {
        _title.value = ""
        _description.value = ""
        _amount.value = ""
        _date.value = Date()
    }


    fun addTransaction(
        title: String,
        description: String,
        amount: String,
        date: Date,
        income: Boolean,
        profileId: Int
    ) {
        viewModelScope.launch {
            wiseRipOffDatabase.transactionDao().insertTransaction(
                Transaction(
                    title = title,
                    description = description,
                    amount = amount.toDouble(),
                    date = date,
                    income = income,
                    profileId = profileId
                )
            )
            wiseRipOffDatabase.profileDao().getProfileBalance(profileId).let {
                val newBalance = if (income) it + amount.toDouble() else it - amount.toDouble()
                wiseRipOffDatabase.profileDao().updateBalance(profileId, newBalance)
            }

        }
    }

}
