package com.example.diseomobile.authentication

import android.content.Context
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainActivityAuthentication @Inject constructor(
    private val biometricAuthManager: BiometricAuthManager
) : ViewModel() {
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    fun authenticate(context : Context) {
        biometricAuthManager.authenticate(
            context = context,
            onError = {
                _isAuthenticated.value = false
            },
            onSuccess = {
                _isAuthenticated.value = true
            },
            onFail = {
                _isAuthenticated.value = false
            }
        )
    }

    fun resetAuthentication() {
        _isAuthenticated.value = false
    }
}