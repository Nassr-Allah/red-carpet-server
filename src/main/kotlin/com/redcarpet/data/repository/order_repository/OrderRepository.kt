package com.redcarpet.data.repository.order_repository

import com.redcarpet.data.model.CustomOrder
import com.redcarpet.data.model.NormalOrder
import com.redcarpet.data.model.Order

interface OrderRepository {

    suspend fun getAllOrders(): List<NormalOrder>

    suspend fun getClientOrders(clientId: String): List<NormalOrder>

    suspend fun getOrderById(id: String): NormalOrder?

    suspend fun insertOrder(order: NormalOrder)

    suspend fun updateOrder(order: NormalOrder)

    suspend fun deleteOrderById(id: String)

}