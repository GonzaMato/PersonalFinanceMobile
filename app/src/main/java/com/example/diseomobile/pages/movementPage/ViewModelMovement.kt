package com.example.diseomobile.pages.movementPage

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.diseomobile.data.WiseRipOffDatabase
import com.example.diseomobile.data.models.transaction.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ViewModelMovement @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel(){

    private val wiseRipOffDatabase = WiseRipOffDatabase.getDataBase(context)

    fun getTransactionById(transactionId: Int): LiveData<Transaction?> = liveData {
        val transaction = wiseRipOffDatabase.transactionDao().getTransactionById(transactionId)
        emit(transaction)
    }
    fun deleteTransaction(transactionId: Int) {
        wiseRipOffDatabase.transactionDao().deleteTransactionById(transactionId)
    }
}