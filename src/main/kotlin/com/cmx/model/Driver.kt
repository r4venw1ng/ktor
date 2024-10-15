package com.cmx.model

import java.util.UUID

data class Driver(
    val user: User,
    val driverLicenseNumber: String,
    val vehicleDetails: Vehicle,
    val serviceHours: ServiceHours,
    val trips: List<Trip>,
    val preStartChecklist: PreStartChecklist,
    val fleetId: UUID
)