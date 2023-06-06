package com.redcarpet.routes

import com.redcarpet.data.model.CustomDesign
import com.redcarpet.data.model.CustomOrder
import com.redcarpet.data.model.Order
import com.redcarpet.data.repository.custom_order_repository.CustomOrderRepository
import com.redcarpet.routes.authenticate
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.customOrderRoute(customOrderRepository: CustomOrderRepository) {
    route("/custom-orders") {

        authenticate {
            get {
                try {
                    val orders = customOrderRepository.getAllCustomOrders()
                    call.respond(orders)
                } catch (e: Exception) {
                    call.respondText(
                        text = e.localizedMessage,
                        status = HttpStatusCode.InternalServerError
                    )
                }
            }

            get("/client/{id}") {
                val clientId = call.parameters["id"] ?: return@get call.respondText(
                    text = "Id can't be null!",
                    status = HttpStatusCode.NotFound
                )
                val orders = customOrderRepository.getClientCustomOrders(clientId)
                call.respond(orders)
            }

            get("/{id}") {
                val id = call.parameters["id"] ?: ""
                val order = customOrderRepository.getCustomOrderById(id) ?: return@get call.respondText(
                    text = "Order does not exist!",
                    status = HttpStatusCode.NotFound
                )
                call.respond(order)
            }

            post {
                val order = call.receive<CustomOrder>()
                println("Order receive: $order")
                try {
                    customOrderRepository.insertCustomOrder(order)
                    call.respondText(
                        text = "Order Added Successfully!",
                        status = HttpStatusCode.Created
                    )
                } catch (e: Exception) {
                    call.respondText(
                        text = e.localizedMessage,
                        status = HttpStatusCode.InternalServerError
                    )
                }
            }

            put {
                val order = call.receive<CustomOrder>()
                try {
                    customOrderRepository.updateCustomOrder(order)
                    call.respondText(
                        text = "Order Updated Successfully!",
                        status = HttpStatusCode.OK
                    )
                } catch (e: Exception) {
                    call.respondText(
                        text = e.localizedMessage,
                        status = HttpStatusCode.InternalServerError
                    )
                }
            }

            delete("/{id}") {
                val id = call.parameters["id"] ?: ""
                try {
                    customOrderRepository.deleteCustomOrderById(id)
                    call.respondText(
                        text = "Order Deleted Successfully!",
                        status = HttpStatusCode.OK
                    )
                } catch (e: Exception) {
                    call.respondText(
                        text = e.localizedMessage,
                        status = HttpStatusCode.InternalServerError
                    )
                }
            }
        }

    }
}