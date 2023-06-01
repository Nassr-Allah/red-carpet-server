package com.redcarpet.routes

import com.redcarpet.data.model.Design
import com.redcarpet.data.repository.design_repository.DesignRepository
import com.redcarpet.routes.authenticate
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.designRoute(designRepository: DesignRepository) {
    route("/designs") {

        authenticate {
            get {
                val designs = designRepository.getAllDesigns()
                call.respond(designs)
            }

            get("/{id}") {
                val id = call.parameters["id"] ?: ""
                val design = designRepository.getDesignById(id) ?: return@get call.respondText(
                    text = "Design does not exist!",
                    status = HttpStatusCode.NotFound
                )
                call.respond(design)
            }

            post {
                val design = call.receive<Design>()
                try {
                    designRepository.insertDesign(design)
                    call.respondText(
                        text = "Design Added Successfully!",
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
                val design = call.receive<Design>()
                try {
                    designRepository.updateDesign(design)
                    call.respondText(
                        text = "Design Updated Successfully!",
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
                    designRepository.deleteDesignById(id)
                    call.respondText(
                        text = "Design Deleted Successfully!",
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