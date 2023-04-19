package com.redcarpet.routes

import com.redcarpet.data.model.OriginalPattern
import com.redcarpet.data.repository.pattern_repository.PatternRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.patternRoute(patternRepository: PatternRepository) {
    route("/patterns") {

        get {
            val patterns = patternRepository.getAllPatterns()
            call.respond(patterns)
        }

        get("/{id}") {
            val id = call.parameters["id"] ?: ""
            val pattern = patternRepository.getPatternById(id) ?: return@get call.respondText(
                text = "This patten does not exist!",
                status = HttpStatusCode.NotFound
            )
            call.respond(pattern)
        }

        post {
            val pattern = call.receive<OriginalPattern>()
            try {
                patternRepository.insertPattern(pattern)
                call.respondText(
                    text = "Pattern Added Successfully!",
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
            val pattern = call.receive<OriginalPattern>()
            try {
                patternRepository.updatePattern(pattern)
                call.respondText(
                    text = "Pattern Updated Successfully!",
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
                patternRepository.deletePatternById(id)
                call.respondText(
                    text = "Pattern Deleted Successfully!",
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