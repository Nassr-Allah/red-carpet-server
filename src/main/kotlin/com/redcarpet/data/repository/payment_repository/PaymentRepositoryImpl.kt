package com.redcarpet.data.repository.payment_repository

import com.redcarpet.data.model.Payment
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class PaymentRepositoryImpl(private val database: CoroutineDatabase): PaymentRepository {

    private val payments = database.getCollection<Payment>()

    override suspend fun getAllPayments(): List<Payment> {
        return payments.find().toList()
    }

    override suspend fun getClientPayments(clientId: String): List<Payment> {
        return payments.find(Payment::clientId eq clientId).toList()
    }

    override suspend fun getPaymentById(id: String): Payment? {
        return payments.findOneById(id)
    }

    override suspend fun getOrderPayment(orderId: String): Payment? {
        return payments.findOne(Payment::orderId eq orderId)
    }

    override suspend fun insertPayment(payment: Payment) {
        payments.insertOne(payment)
    }

    override suspend fun updatePayment(payment: Payment) {
        payments.updateOneById(payment.id, payment)
    }

    override suspend fun deletePaymentById(id: String) {
        payments.deleteOneById(id)
    }
}