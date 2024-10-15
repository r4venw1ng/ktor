package com.cmx.model

import java.util.UUID

data class UnloadingPeriod(
    val start: String,
    val end: String,
    val duration: Int,
    val tripId: UUID,
    val latitude: Double,
    val longitude: Double
)
