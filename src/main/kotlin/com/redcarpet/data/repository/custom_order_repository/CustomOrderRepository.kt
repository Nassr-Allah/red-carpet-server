package com.redcarpet.data.repository.custom_order_repository

import com.redcarpet.data.model.CustomOrder

interface CustomOrderRepository {

    suspend fun getAllCustomOrders(): List<CustomOrder>

    suspend fun getClientCustomOrders(clientId: String): List<CustomOrder>

    suspend fun getCustomOrderById(id: String): CustomOrder?

    suspend fun insertCustomOrder(order: CustomOrder)

    suspend fun updateCustomOrder(order: CustomOrder)

    suspend fun deleteCustomOrderById(id: String)

}