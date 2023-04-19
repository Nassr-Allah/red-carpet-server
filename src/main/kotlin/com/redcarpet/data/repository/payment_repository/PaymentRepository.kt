package com.redcarpet.data.repository.payment_repository

import com.redcarpet.data.model.Payment

interface PaymentRepository {

    suspend fun getAllPayments(): List<Payment>

    suspend fun getClientPayments(clientId: String): List<Payment>

    suspend fun getPaymentById(id: String): Payment?

    suspend fun getOrderPayment(orderId: String): Payment?

    suspend fun insertPayment(payment: Payment)

    suspend fun updatePayment(payment: Payment)

    suspend fun deletePaymentById(id: String)

}