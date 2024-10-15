package com.cmx.service

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.cmx.repository.UserRepository
import com.cmx.routing.request.LoginRequest
import io.ktor.server.application.Application
import io.ktor.server.auth.DigestCredential
import io.ktor.server.auth.jwt.JWTCredential
import io.ktor.server.auth.jwt.JWTPrincipal
import java.util.Date
import javax.management.relation.Role

class JwtService (
    private val application: Application,
    private val userRepository: UserRepository
) {

    private val secret = getConfigProperty("jwt.secret")
    private val issuer = getConfigProperty("jwt.issuer")
    private val audience = getConfigProperty("jwt.audience")

    val realm = getConfigProperty("jwt.realm")

    val jwtVerifier: JWTVerifier =
        JWT
            .require(Algorithm.HMAC256(secret))
            .withAudience(audience)
            .withIssuer(issuer)
            .build()

    fun createAccessToken(email: String, role: String): String =
        createJwtToken(email, role, 3_600_000)

    fun createRefreshToken(email: String, role: String): String =
        createJwtToken(email, role, 86_400_00)

    fun createJwtToken(
        email: String,
        role: String,
        expireInt: Int,
        ): String =
            JWT
                .create()
                .withAudience(audience)
                .withIssuer(issuer)
                .withClaim("email", email)
                .withClaim("role", role)
                .withExpiresAt(Date(System.currentTimeMillis() + expireInt))
                .sign(Algorithm.HMAC256(secret))

    fun customValidator(credential: JWTCredential): JWTPrincipal? {
        val email = extractEmail(credential)
        val foundUser = email?.let(userRepository::findByEmail)

        return foundUser?.let {
            if(audienceMatches(credential)) {
                JWTPrincipal(credential.payload)
            } else null
        }
    }

    fun audienceMatches(audience: String): Boolean =
        this.audience == audience

    private fun audienceMatches(credential: JWTCredential): Boolean =
        credential.payload.audience.contains(audience)

    private fun extractEmail(credential: JWTCredential): String? =
        credential.payload.getClaim("email").asString()

    private fun getConfigProperty(path: String) =
        application.environment.config.property(path).getString()
}