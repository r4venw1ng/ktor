package com.cmx

import com.cmx.repository.UserRepository
import com.cmx.routing.configureRouting
import com.cmx.service.UserService
import com.cmx.plugins.*
import com.cmx.repository.RefreshTokenRepository
import com.cmx.service.JwtService
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val userRepository = UserRepository()
    val jwtService = JwtService(this, userRepository)
    val refreshTokenRepository = RefreshTokenRepository()
    val userService = UserService(userRepository, jwtService, refreshTokenRepository)


    configureSerialization()
    configureSecurity(jwtService)
    configureRouting(userService)
}
