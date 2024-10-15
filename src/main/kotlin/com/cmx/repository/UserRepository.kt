package com.cmx.repository

import com.cmx.model.User
import java.util.UUID

class UserRepository {

    private val users = mutableListOf<User>(
        User(UUID.randomUUID(), "admin", "password","ADMIN")
    )

    fun findAll(): List<User> =
        users

    fun findByID(id: UUID): User? =
        users.firstOrNull() { it.id == id}

    fun findByEmail(email: String): User? =
        users.firstOrNull { it.email == email}

    fun save(user: User): Boolean =
        users.add(user)
}