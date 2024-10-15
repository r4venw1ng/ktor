package com.cmx.model

import java.util.UUID

data class MealPeriod(
    val start: String,
    val end: String,
    val duration: Int,
    val tripId: UUID?,
    val latitude: Double,
    val longitude: Double
)
