package com.cmx.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.createRouteScopedPlugin
import io.ktor.server.auth.AuthenticationChecked
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.principal
import io.ktor.server.response.respond

class PluginConfiguration {
    var roles: Set<String> = emptySet()
}

val RoleBasedAuthorizationPlugin = createRouteScopedPlugin(
    name = "RbacPlugin",
    createConfiguration = ::PluginConfiguration
) {
    val roles = pluginConfig.roles

    pluginConfig.apply {
        
        on(AuthenticationChecked) { call ->
            val tokenRole = getRoleFromToken(call)

            val authorized = roles.contains(tokenRole)

            if (!authorized) {
                println("The user does not have any of the following roles: $roles")
                call.respond(HttpStatusCode.Forbidden)
            }
        }   
    }
}




private fun getRoleFromToken(call: ApplicationCall): String? =
    call.principal<JWTPrincipal>()
        ?.payload
        ?.getClaim("role")
        ?.asString()