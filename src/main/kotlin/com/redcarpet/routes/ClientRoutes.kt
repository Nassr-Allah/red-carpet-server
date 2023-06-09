package com.redcarpet.routes

import com.redcarpet.data.model.Client
import com.redcarpet.data.model.ClientCollection
import com.redcarpet.data.repository.client_repository.ClientRepository
import com.redcarpet.data.repository.collection_repository.CollectionRepository
import com.redcarpet.routes.authenticate
import com.redcarpet.security.hashing.HashingService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.clientRoute(
    clientRepository: ClientRepository,
    hashingService: HashingService,
    collectionRepository: CollectionRepository
) {

    route("/clients") {

        get {
            val clients = clientRepository.getAllClients()
            call.respond(clients)
        }

        authenticate {
            get("/{id}") {
                val clientId = call.parameters["id"] ?: ""
                val client = clientRepository.getClientById(clientId) ?: return@get call.respondText(
                    "Client Not Found",
                    status = HttpStatusCode.NotFound
                )
                call.respond(client)
            }

            get("/phone/{phoneNumber}") {
                val phoneNumber = call.parameters["phoneNumber"] ?: ""
                val client = clientRepository.getClientByPhone(phoneNumber) ?: return@get call.respondText(
                    "Client with this Phone Number does not exist",
                    status = HttpStatusCode.NotFound
                )
                call.respond(client)
            }
        }

        post {
            var client = call.receive<Client>()
            val hashedPassword = hashingService.generateHash(client.password)
            client = client.copy(password = hashedPassword)
            try {
                clientRepository.insertClient(client)
                val collection = ClientCollection(
                    designs = emptyList(),
                    patterns = emptyList(),
                    clientId = client.id
                )
                collectionRepository.insertCollection(collection)
                call.respondText("Client Added Successfully!", status = HttpStatusCode.Created)
            } catch (e: Exception) {
                call.respondText(e.localizedMessage, status = HttpStatusCode.InternalServerError)
            }
        }

        authenticate {
            put {
                val client = call.receive<Client>()
                try {
                    clientRepository.updateClient(client)
                    call.respondText("Client Updated Successfully!", status = HttpStatusCode.OK)
                } catch (e: Exception) {
                    call.respondText(e.localizedMessage, status = HttpStatusCode.InternalServerError)
                }
            }

            delete("/{id}") {
                val id = call.parameters["id"] ?: ""
                try {
                    clientRepository.deleteClientById(id)
                    call.respondText("Client Deleted Successfully!", status = HttpStatusCode.OK)
                } catch (e: Exception) {
                    call.respondText(e.localizedMessage, status = HttpStatusCode.InternalServerError)
                }
            }
        }
    }
}