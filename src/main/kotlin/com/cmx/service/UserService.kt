package com.cmx.service

import com.auth0.jwt.interfaces.DecodedJWT
import com.cmx.model.User
import com.cmx.repository.RefreshTokenRepository
import com.cmx.repository.UserRepository
import com.cmx.routing.request.LoginRequest
import com.cmx.routing.response.AuthResponse
import java.util.UUID


class UserService(
    private val userRepository: UserRepository,
    private val jwtService: JwtService,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun findAll(): List<User> =
        userRepository.findAll()

    fun findById(id: String): User? =
        userRepository.findByID(
            id = UUID.fromString(id)
        )

    fun findByEmail(email: String): User? =
        userRepository.findByEmail(email)

    fun save(user: User): User? {
        val foundUser = findByEmail(user.email)

        return if (foundUser == null) {
            userRepository.save(user)
            user
        } else null
    }

    fun authenticate(loginRequest: LoginRequest): AuthResponse? {
        val email = loginRequest.email
        val foundUser = userRepository.findByEmail(email)

        return  if(foundUser != null && foundUser.password == loginRequest.password) {
            val accessToken = jwtService.createAccessToken(email, foundUser.role)
            val refreshToken = jwtService.createRefreshToken(email, foundUser.role)

            refreshTokenRepository.save(refreshToken, email)

            AuthResponse(
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        } else null
    }

    fun refreshToken(token: String): String? {
        val decodedRefreshToken = verifyRefreshToken(token)
        val persistedEmail = refreshTokenRepository.findEmailByToken(token)

        return if (decodedRefreshToken != null && persistedEmail != null) {
            val foundEmail = userRepository.findByEmail(persistedEmail)
            val emailFromRefreshToken = decodedRefreshToken.getClaim("email").asString()

            if(foundEmail != null && emailFromRefreshToken == foundEmail.email)
                jwtService.createAccessToken(persistedEmail, foundEmail.role)
            else
                null
        }else
            null
    }

    private fun verifyRefreshToken(token: String) : DecodedJWT? {
        val decodedJWT = decodedJWT(token)

        return decodedJWT?.let {
            val audienceMatches = jwtService.audienceMatches(it.audience.first())

            if(audienceMatches)
                decodedJWT
            else
                null
        }
    }

    private fun decodedJWT(token: String) = try {
        jwtService.jwtVerifier.verify(token)
    } catch (ex: Exception) {
        null
    }
}