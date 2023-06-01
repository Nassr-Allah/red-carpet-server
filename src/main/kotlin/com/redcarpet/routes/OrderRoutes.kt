package com.redcarpet.routes

import com.redcarpet.data.model.NormalOrder
import com.redcarpet.data.model.Order
import com.redcarpet.data.repository.order_repository.OrderRepository
import com.redcarpet.routes.authenticate
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.orderRoute(orderRepository: OrderRepository) {
    route("/orders") {

        authenticate {
            get {
                val orders = orderRepository.getAllOrders()
                call.respond(orders)
            }

            get("/client/{id}") {
                val id = call.parameters["id"] ?: ""
                val orders = orderRepository.getClientOrders(id)
                call.respond(orders)
            }

            get("{id}") {
                val id = call.parameters["id"] ?: ""
                val order = orderRepository.getOrderById(id) ?: return@get call.respondText(
                    text = "This order does not exist!",
                    status = HttpStatusCode.NotFound
                )
                call.respond(order)
            }

            post {
                val order = call.receive<NormalOrder>()
                try {
                    orderRepository.insertOrder(order)
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
                val order = call.receive<NormalOrder>()
                try {
                    orderRepository.updateOrder(order)
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
                    orderRepository.deleteOrderById(id)
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