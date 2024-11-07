package com.example.diseomobile.pages.movementPage

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.diseomobile.data.WiseRipOffDatabase
import com.example.diseomobile.data.models.transaction.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelMovement @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val wiseRipOffDatabase = WiseRipOffDatabase.getDataBase(context)

    fun getTransactionById(transactionId: Int): LiveData<Transaction?> = liveData {
        val transaction = wiseRipOffDatabase.transactionDao().getTransactionById(transactionId)
        emit(transaction)
    }

    fun deleteTransaction(transactionId: Int) {
        viewModelScope.launch(Dispatchers.IO) { // Launch a coroutine on the IO dispatcher
            val transaction = wiseRipOffDatabase.transactionDao().getTransactionById(transactionId)
            if (transaction != null) { // Check if transaction is not null
                val amount = transaction.amount
                val income = transaction.income
                wiseRipOffDatabase.transactionDao().deleteTransactionById(transactionId)
                val balance = wiseRipOffDatabase.profileDao().getProfileBalance(1)
                val newBalance = if (income) balance - amount else balance + amount

                wiseRipOffDatabase.profileDao().updateBalance(1, newBalance)
            }
        }
    }
}