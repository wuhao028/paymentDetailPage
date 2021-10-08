package com.wuhao.paymentpage.data.model

data class TransactionState(
    var cardNumber: String? = null,
    var scurityCode: Int? = null,
    var expireData: String? = null,
    var amount: Int? = null,
    var currency: String? = null
)