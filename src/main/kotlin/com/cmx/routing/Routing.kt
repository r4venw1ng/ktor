package com.cmx.routing

import com.cmx.service.JwtService
import com.cmx.service.UserService
import io.ktor.server.application.Application
import io.ktor.server.routing.route
import io.ktor.server.routing.routing


fun Application.configureRouting(
    userService: UserService,
) {

    routing {

        route("/api/auth") {
            authRoute(userService)
        }

        route("/api/user") {
            userRoute(userService)
        }
    }
}