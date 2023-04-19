package com.redcarpet.routes

import com.redcarpet.data.model.ClientCollection
import com.redcarpet.data.repository.collection_repository.CollectionRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.collectionRoute(collectionRepository: CollectionRepository) {
    route("/collections") {

        get {
            val collections = collectionRepository.getAllCollections()
            call.respond(collections)
        }

        get("/client/{id}") {
            val id = call.parameters["id"] ?: ""
            val collection = collectionRepository.getClientCollection(id) ?: return@get call.respondText(
                text = "Collection does not exist",
                status = HttpStatusCode.NotFound
            )
            call.respond(collection)
        }

        get("/{id}") {
            val id = call.parameters["id"] ?: ""
            val collection = collectionRepository.getCollectionById(id) ?: return@get call.respondText(
                text = "Collection does not exist",
                status = HttpStatusCode.NotFound
            )
            call.respond(collection)
        }

        post {
            val collection = call.receive<ClientCollection>()
            try {
                collectionRepository.insertCollection(collection)
                call.respondText(
                    text = "Collection Added Successfully!",
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
            val collection = call.receive<ClientCollection>()
            try {
                collectionRepository.updateCollection(collection)
                call.respondText(
                    text = "Collection Updated Successfully!",
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
                collectionRepository.deleteCollectionById(id)
                call.respondText(
                    text = "Collection Deleted Successfully!",
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