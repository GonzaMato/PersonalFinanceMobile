package com.example.diseomobile.pages.homePage

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
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

    val transactions = wiseRipOffDatabase.transactionDao().getTransactions(1).asFlow()

    val balance = wiseRipOffDatabase.profileDao().getProfileBalanceLiveData(1).asFlow()

    val nameProfile = wiseRipOffDatabase.profileDao().getProfileName(1).asFlow()

    fun createProfileIfNonExistant(name : String) {
        viewModelScope.launch {
            val profile = wiseRipOffDatabase.profileDao().getProfileById(1)
            if (profile == null) {
                wiseRipOffDatabase.profileDao().insertProfile(
                    Profile(
                        name = name,
                        balance = 0.0
                    )
                )
            }
        }
    }

}