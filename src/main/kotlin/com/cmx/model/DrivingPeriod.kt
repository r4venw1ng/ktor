package com.cmx.model

import java.util.UUID

data class DrivingPeriod(
    val start: String,
    val end: String,
    val duration: Int,
    val tripId: UUID,
    val startLatitude: Double,
    val startLongitude: Double,
    val endLatitude: Double,
    val endLongitude: Double
)
