package com.redcarpet

import com.redcarpet.di.appModule
import io.ktor.server.application.*
import com.redcarpet.plugins.*
import com.redcarpet.security.hashing.SHA256HashingService
import com.redcarpet.security.token.JwtTokenService
import com.redcarpet.security.token.TokenConfig
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    val tokenService = JwtTokenService()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiry = 7889400000L,
        secret = System.getenv("JWT_SECRET")
    )
    val hashingService = SHA256HashingService()

    install(Koin) {
        modules(appModule)
    }

    configureSecurity(tokenConfig)
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureRouting(hashingService, tokenService, tokenConfig)
}
