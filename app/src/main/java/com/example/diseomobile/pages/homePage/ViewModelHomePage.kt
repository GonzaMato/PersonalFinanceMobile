package com.example.diseomobile.pages.homePage

import android.content.Context
import android.media.audiofx.DynamicsProcessing.Limiter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diseomobile.data.WiseRipOffDatabase
import com.example.diseomobile.data.models.profile.Profile
import com.example.diseomobile.data.models.transaction.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelHomePage @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val wiseRipOffDatabase = WiseRipOffDatabase.getDataBase(context)

    private val _transactions = MutableStateFlow<List<Transaction>>(listOf())
    val transactions = _transactions.asStateFlow()

    private val _balance = MutableStateFlow<Double?>(0.0) // Hold the balance
    val balance = _balance.asStateFlow()



    fun loadProfileBalance(profileId: Int) {
        viewModelScope.launch {
            _balance.value = wiseRipOffDatabase.profileDao().getProfileBalance(profileId)
        }
    }

    suspend fun getTransactions(profileId: Int, limit: Int, offset: Int) : List<Transaction> {
        return wiseRipOffDatabase.transactionDao().getTransactionsWithPagination(profileId, limit, offset)
    }

    fun createProfileIfNonExistant(){
        viewModelScope.launch {
            val profile = wiseRipOffDatabase.profileDao().getProfileById(1)
            if (profile == null) {
                // Insert the profile if it doesn't exist
                wiseRipOffDatabase.profileDao().insertProfile(Profile(1, "Gonzalo Mato", 0.0))
            }
        }
    }

}