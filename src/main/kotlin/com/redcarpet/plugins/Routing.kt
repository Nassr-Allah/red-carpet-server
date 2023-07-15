package com.redcarpet.plugins

import com.redcarpet.data.repository.client_repository.ClientRepository
import com.redcarpet.data.repository.collection_repository.CollectionRepository
import com.redcarpet.data.repository.course_repository.CourseRepository
import com.redcarpet.data.repository.custom_order_repository.CustomOrderRepository
import com.redcarpet.data.repository.design_repository.DesignRepository
import com.redcarpet.data.repository.manager_repository.ManagerRepository
import com.redcarpet.data.repository.order_repository.OrderRepository
import com.redcarpet.data.repository.pattern_repository.PatternRepository
import com.redcarpet.data.repository.payment_repository.PaymentRepository
import com.redcarpet.data.repository.registration_repository.RegistrationRepository
import com.redcarpet.routes.*
import com.redcarpet.security.hashing.HashingService
import com.redcarpet.security.token.TokenConfig
import com.redcarpet.security.token.TokenService
import io.ktor.client.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting(
    hashingService: HashingService,
    tokenService: TokenService,
    tokenConfig: TokenConfig,
    httpClient: HttpClient
) {

    val clientRepository by inject<ClientRepository>()
    val collectionRepository by inject<CollectionRepository>()
    val courseRepository by inject<CourseRepository>()
    val customOrderRepository by inject<CustomOrderRepository>()
    val designRepository by inject<DesignRepository>()
    val orderRepository by inject<OrderRepository>()
    val patternRepository by inject<PatternRepository>()
    val paymentRepository by inject<PaymentRepository>()
    val registrationRepository by inject<RegistrationRepository>()
    val managerRepository by inject<ManagerRepository>()

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        authRoute(clientRepository, managerRepository, hashingService, tokenService, tokenConfig)
        clientRoute(clientRepository, hashingService, collectionRepository)
        collectionRoute(collectionRepository)
        courseRoute(courseRepository)
        customOrderRoute(customOrderRepository)
        designRoute(designRepository)
        orderRoute(orderRepository)
        patternRoute(patternRepository)
        paymentRoute(paymentRepository)
        registrationRoute(registrationRepository)
        imageRoutes()

        static("uploads") {
            resources("uploads")
            files("uploads")
        }
    }
}
