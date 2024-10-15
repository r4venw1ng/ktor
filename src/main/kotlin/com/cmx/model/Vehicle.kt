package com.cmx.model

import java.util.UUID

data class Vehicle(
    val id: UUID,
    val tractorNumber: String,
    val trailerNumber1: String?,
    val trailerNumber2: String?,
    val tractorPlate: String,
    val trailerPlate1: String?,
    val trailerPlate2: String?,
    val vehicleModel: String,
    val fleetId: UUID
)
