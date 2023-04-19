package com.redcarpet.data.repository.custom_order_repository

import com.redcarpet.data.model.CustomOrder
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.or

class CustomOrderRepositoryImpl(private val database: CoroutineDatabase): CustomOrderRepository {

    private val orders = database.getCollection<CustomOrder>()

    override suspend fun getAllCustomOrders(): List<CustomOrder> {
        return orders.find().toList()
    }

    override suspend fun getClientCustomOrders(clientId: String): List<CustomOrder> {
        return orders.find(CustomOrder::clientId eq clientId).toList()
    }

    override suspend fun getCustomOrderById(id: String): CustomOrder? {
        return orders.findOneById(id)
    }

    override suspend fun insertCustomOrder(order: CustomOrder) {
        orders.insertOne(order)
    }

    override suspend fun updateCustomOrder(order: CustomOrder) {
        orders.updateOneById(order.id, order)
    }

    override suspend fun deleteCustomOrderById(id: String) {
        orders.deleteOneById(id)
    }
}