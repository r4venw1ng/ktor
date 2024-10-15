package com.cmx.model

import java.util.UUID

data class ServiceHours(
    val id: UUID,
    val date: String,
    val workStart: String,
    val workEnd: String,
    val drivingPeriods: List<DrivingPeriod>,
    val restPeriods: List<RestPeriod>,
    val loadingPeriods: List<LoadingPeriod>,
    val unloadingPeriods: List<UnloadingPeriod>,
    val mealPeriods: List<MealPeriod>,
    val trips: List<Trip>,
    val totalHours: Int
)