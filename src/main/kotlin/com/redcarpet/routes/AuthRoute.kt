package com.redcarpet.routes

import com.redcarpet.data.model.AuthRequest
import com.redcarpet.data.model.AuthResponse
import com.redcarpet.data.repository.client_repository.ClientRepository
import com.redcarpet.data.repository.manager_repository.ManagerRepository
import com.redcarpet.security.hashing.HashingService
import com.redcarpet.security.token.TokenClaim
import com.redcarpet.security.token.TokenConfig
import com.redcarpet.security.token.TokenService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.authRoute(
    clientRepository: ClientRepository,
    managerRepository: ManagerRepository,
    hashingService: HashingService,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {

    route("/auth") {

        post("/login") {
            val request = call.receive<AuthRequest>()
            val client = clientRepository.getClientByPhone(request.phoneNumber) ?: return@post call.respondText(
                text = "Client with this phone number does not exist!",
                status = HttpStatusCode.NotFound
            )
            val isPasswordValid = hashingService.verify(request.password, client.password)
            if (!isPasswordValid) {
                call.respondText(
                    text = "Wrong Password!",
                    status = HttpStatusCode.Conflict
                )
            }
            val token = tokenService.generate(
                config = tokenConfig,
                TokenClaim(
                    name = "clientId",
                    value = client.id
                )
            )
            call.respond(
                status = HttpStatusCode.OK,
                message = AuthResponse(token, client.id)
            )
        }

        post("/login-manager") {
            val request = call.receive<AuthRequest>()
            val manager = managerRepository.getManagerByUsername(request.phoneNumber) ?: return@post call.respondText(
                text = "Manager with this username does not exist!",
                status = HttpStatusCode.NotFound
            )
            val isPasswordValid = hashingService.verify(request.password, manager.password)
            if (!isPasswordValid) {
                call.respondText(
                    text = "Wrong Password!",
                    status = HttpStatusCode.Conflict
                )
            }
            val token = tokenService.generate(
                config = tokenConfig,
                TokenClaim(
                    name = "clientId",
                    value = manager.id
                )
            )
            call.respond(
                status = HttpStatusCode.OK,
                message = AuthResponse(token, manager.id)
            )
        }

    }
}

fun Route.authenticate() {
    authenticate {
        get("authenticate") {
            call.respond(HttpStatusCode.OK)
        }
    }
}
