package com.wuhao.paymentpage.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.wuhao.paymentpage.data.model.TransactionState

class PaymentViewModel : ViewModel() {

    private val _state = mutableStateOf(TransactionState())
    val cardNumber = mutableStateOf("")
    val expireDate = mutableStateOf("")
    val securityCode = mutableStateOf("")
    val state: State<TransactionState> = _state

    init {
        state.value.amount = 200
        state.value.currency = "$"
    }

    fun changeMotoType() {

    }

    fun continuePay() {

    }

}