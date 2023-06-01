package com.redcarpet.routes

import com.redcarpet.data.model.Payment
import com.redcarpet.data.repository.payment_repository.PaymentRepository
import com.redcarpet.routes.authenticate
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.paymentRoute(paymentRepository: PaymentRepository) {
    route("/payments") {

        authenticate {
            get {
                val payments = paymentRepository.getAllPayments()
                call.respond(payments)
            }

            get("/client/{id}") {
                val id = call.parameters["id"] ?: ""
                val payments = paymentRepository.getClientPayments(id)
                call.respond(payments)
            }

            get("/order/{id}") {
                val id = call.parameters["id"] ?: ""
                val payment = paymentRepository.getOrderPayment(id) ?: return@get call.respondText(
                    text = "This payment does not exist!",
                    status = HttpStatusCode.NotFound
                )
                call.respond(payment)
            }

            get("/{id}") {
                val id = call.parameters["id"] ?: ""
                val payment = paymentRepository.getPaymentById(id) ?: return@get call.respondText(
                    text = "This payment does not exist!",
                    status = HttpStatusCode.NotFound
                )
                call.respond(payment)
            }

            post {
                val payment = call.receive<Payment>()
                try {
                    paymentRepository.insertPayment(payment)
                    call.respondText(
                        text = "Payment Created Successfully!",
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
                val payment = call.receive<Payment>()
                try {
                    paymentRepository.updatePayment(payment)
                    call.respondText(
                        text = "Payment Updated Successfully!",
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
                    paymentRepository.deletePaymentById(id)
                    call.respondText(
                        text = "Payment Deleted Successfully!",
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