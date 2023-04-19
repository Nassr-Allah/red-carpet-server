package com.redcarpet.routes

import com.redcarpet.data.model.Registration
import com.redcarpet.data.repository.registration_repository.RegistrationRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.registrationRoute(registrationRepository: RegistrationRepository) {
    route("/registrations") {

        get {
            val registrations = registrationRepository.getAllRegistrations()
            call.respond(registrations)
        }

        get("/client/{id}") {
            val id = call.parameters["id"] ?: ""
            val registrations = registrationRepository.getClientRegistrations(id)
            call.respond(registrations)
        }

        get("{id}") {
            val id = call.parameters["id"] ?: ""
            val registration = registrationRepository.getRegistrationById(id) ?: return@get call.respondText(
                text = "Registration does not exist!",
                status = HttpStatusCode.NotFound
            )
            call.respond(registration)
        }

        post {
            val registration = call.receive<Registration>()
            try {
                registrationRepository.insertRegistration(registration)
                call.respondText(
                    text = "Registration Added Successfully!",
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
            val registration = call.receive<Registration>()
            try {
                registrationRepository.updateRegistration(registration)
                call.respondText(
                    text = "Registration Updated Successfully!",
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
                registrationRepository.deleteRegistrationById(id)
                call.respondText(
                    text = "Registration Deleted Successfully!",
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