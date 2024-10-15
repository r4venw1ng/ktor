package com.cmx.routing

import com.cmx.model.User
import com.cmx.routing.request.UserRequest
import com.cmx.routing.response.UserResponse
import com.cmx.service.UserService
import com.cmx.util.authorized
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.request.receive
import io.ktor.server.response.header
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.get
import java.util.UUID
import javax.management.relation.Role

fun Route.userRoute(
    userService: UserService
) {

    post {
        val userRequest = call.receive<UserRequest>()

        val createdUser = userService.save(
            user = userRequest.toModel()
        ) ?: return@post call.respond(HttpStatusCode.BadRequest, "Invalid data or user not created")

        call.response.header(
            HttpHeaders.Location, "/api/user/${createdUser.id}"
        )

        call.respond(
            HttpStatusCode.Created,
            mapOf("id" to createdUser.id.toString(), "message" to "User created successfully")
        )
    }

    authenticate {
        authorized("ADMIN") {
            get {
                val users = userService.findAll()

                call.respond(
                    message = users.map(User::toResponse)
                )
            }
        }
    }

    authenticate ("another-auth") {
        authorized("ADMIN", "USER") {
            get("/{id}") {
                val id: String = call.parameters["id"]
                    ?: return@get call.respond(HttpStatusCode.BadRequest)

                val foundUser = userService.findById(id)
                    ?: return@get call.respond(HttpStatusCode.NotFound)

                if(foundUser.email!= extractPrincipalEmail(call))
                    return@get call.respond(HttpStatusCode.NotFound)

                call.respond(
                    message = foundUser.toResponse()
                )
            }
        }
    }
}

fun extractPrincipalEmail(call: ApplicationCall): String? =
    call.principal<JWTPrincipal>()
        ?.payload
        ?.getClaim("email")
        ?.asString()

private fun UserRequest.toModel(): User =
    User(
        id = UUID.randomUUID(),
        email = this.email,
        password = this.password,
        role = "USER"
    )

private fun User.toResponse(): UserResponse =
    UserResponse(
        id = this.id,
        email= this.email
    )