package com.cmx.model

import java.util.UUID

data class Trip(
    val id: UUID,  // Identificador único del viaje
    val startLocationLatitude: Double,  // Latitud de la ubicación de inicio
    val startLocationLongitude: Double,  // Longitud de la ubicación de inicio
    val endLocationLatitude: Double,  // Latitud del destino
    val endLocationLongitude: Double,  // Longitud del destino
    val waypoints: List<Pair<Double, Double>>?,  // Lista opcional de puntos intermedios (waypoints)
    val distance: Double?,  // Distancia total del viaje (en kilómetros)
    val eta: String?,  // Tiempo estimado de llegada (formato HH:mm)
    val drivingPeriods: List<DrivingPeriod>,  // Lapsos de conducción relacionados con el viaje
    val restPeriods: List<RestPeriod>,  // Lapsos de descanso relacionados con el viaje
    val loadingPeriods: List<LoadingPeriod>,  // Lapsos de carga relacionados con el viaje
    val unloadingPeriods: List<UnloadingPeriod>,  // Lapsos de descarga relacionados con el viaje
    val mealPeriods: List<MealPeriod>,  // Lapsos de comida relacionados con el viaje
    val preStartChecklist: PreStartChecklist  // Checklist previo al viaje
)
