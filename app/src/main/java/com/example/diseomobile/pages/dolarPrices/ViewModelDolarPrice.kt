package com.example.diseomobile.pages.dolarPrices

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diseomobile.api.DolarApiServiceImpl
import com.example.diseomobile.api.DolarPrice
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelDolarPrice @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dolarApiServiceImpl: DolarApiServiceImpl
) : ViewModel() {

    private val _loadingPrices = MutableStateFlow<Boolean>(false)
    val loadingPrices = _loadingPrices.asStateFlow()

    private val _dolarPrices = MutableStateFlow<List<DolarPrice>>(listOf())
    val dolarPrices = _dolarPrices.asStateFlow()

    private val _showRetry = MutableStateFlow<Boolean>(false)
    val showRetry = _showRetry.asStateFlow()


    init {
        loadPrices()
    }

    fun retry() {
        _showRetry.value = false
        loadPrices()
    }

    private fun loadPrices() {
        _loadingPrices.value = true
        dolarApiServiceImpl.getDolarPrice(
            context = context,
            onSuccess = {
                viewModelScope.launch {
                    _dolarPrices.emit(it)
                }
                _loadingPrices.value = false
            },
            onFail = {
                _showRetry.value = true
                _loadingPrices.value = false
            },
            loadingFinished = {
                _loadingPrices.value = false
            }
        )
    }

}