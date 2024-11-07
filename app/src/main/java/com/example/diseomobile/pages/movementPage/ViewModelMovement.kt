package com.example.diseomobile.pages.movementPage

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.diseomobile.data.WiseRipOffDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class ViewModelMovement @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel(){

    private val wiseRipOffDatabase = WiseRipOffDatabase.getDataBase(context)

    fun deleteTransaction(transactionId: Int) {
        wiseRipOffDatabase.transactionDao().deleteTransactionById(transactionId)
    }
}