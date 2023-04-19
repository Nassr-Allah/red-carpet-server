package com.redcarpet.data.repository.order_repository

import com.redcarpet.data.model.NormalOrder
import com.redcarpet.data.model.Order
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class OrderRepositoryImpl(private val database: CoroutineDatabase): OrderRepository {

    private val orders = database.getCollection<NormalOrder>()

    override suspend fun getAllOrders(): List<NormalOrder> {
        return orders.find().toList()
    }

    override suspend fun getClientOrders(clientId: String): List<NormalOrder> {
        return orders.find(NormalOrder::clientId eq clientId).toList()
    }

    override suspend fun getOrderById(id: String): NormalOrder? {
        return orders.findOneById(id)
    }

    override suspend fun insertOrder(order: NormalOrder) {
        orders.insertOne(order)
    }

    override suspend fun updateOrder(order: NormalOrder) {
        orders.updateOneById(order.id, order)
    }

    override suspend fun deleteOrderById(id: String) {
        orders.deleteOneById(id)
    }
}