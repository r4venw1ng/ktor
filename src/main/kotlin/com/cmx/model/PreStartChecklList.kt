package com.cmx.model

import java.util.UUID

data class PreStartChecklist(
    val id: UUID,  // Identificador único de la bitácora de checklist
    val tripId: UUID,  // Relación con el viaje
    val date: String,  // Fecha en la que se realizó el checklist (formato YYYY-MM-DD)
    val time: String,  // Hora en la que se realizó el checklist
    val plateTracto: String,  // Placa del tracto
    val plateRemolque1: String,  // Placa del primer remolque
    val plateRemolque2: String?,  // Placa del segundo remolque (opcional)
    val kilometer: Int,  // Kilometraje al iniciar el viaje
    // Sección de Motor
    val engineOilLevel: Boolean,  // Nivel de aceite del motor
    val coolantLevel: Boolean,  // Nivel de anticongelante
    val steeringOilLevel: Boolean,  // Nivel de aceite de dirección
    val oilLeaks: Boolean,  // Fugas de aceite, combustible o anticongelante
    val batteryStatus: Boolean,  // Estado de las baterías
    // Sección de Transmisión
    val transmissionIssues: Boolean,  // Fallas en la transmisión
    val activeCodes: Boolean,  // Códigos activos o inactivos en la transmisión
    val clutchCondition: Boolean,  // Estado del clutch
    // Sección de Dirección
    val wheelCondition: Boolean,  // Estado de las ruedas
    val vibrations: Boolean,  // Vibraciones o balanceo del vehículo
    // Sección de Llantas y Frenos
    val tirePressure: Boolean,  // Presión de las llantas
    val tireWear: Boolean,  // Desgaste de las llantas
    val brakeCondition: Boolean,  // Estado de los frenos
    // Sección de Tracto (carrocería y luces)
    val lightsCondition: Boolean,  // Estado de las luces del tracto
    val mirrorsCondition: Boolean,  // Estado de los espejos
    val windshieldWipers: Boolean,  // Estado de los limpiaparabrisas
    val interiorCabin: Boolean,  // Estado del interior de la cabina
    // Sección de Carrocería (remolques y luces)
    val trailerLights: Boolean,  // Estado de las luces de los remolques
    val bodyworkCondition: Boolean,  // Estado de la carrocería
    val fenderCondition: Boolean,  // Estado de los guardafangos
    // Sistema de Enganche
    val couplingCondition: Boolean,  // Estado del sistema de acoplamiento (enganche)
    val kingpinCondition: Boolean,  // Estado del perno rey
    val latchesCondition: Boolean,  // Estado de los pestillos
    // Sistema de Descarga (remolques)
    val dischargeSystem: Boolean,  // Estado del sistema de descarga
    val dischargeValves: Boolean,  // Estado de válvulas y llaves del sistema de descarga
    // Suspensión y Frenos (remolques)
    val suspensionCondition: Boolean,  // Estado de la suspensión
    val brakePadsCondition: Boolean,  // Estado de las balatas de los remolques
    // Llantas (remolques)
    val trailerTireCondition: Boolean,  // Estado de las llantas de los remolques
    val trailerTirePressure: Boolean,  // Presión de las llantas de los remolques
    // Documentación
    val tractoPlates: Boolean,  // Placas del tracto
    val remolquePlates: Boolean,  // Placas de los remolques
    val circulationCard: Boolean,  // Tarjeta de circulación
    val insurancePolicy: Boolean,  // Póliza de seguro
    // Equipo de seguridad
    val triangles: Boolean,  // Triángulos de seguridad
    val jack: Boolean,  // Gato hidráulico
    val fireExtinguishers: Boolean,  // Extintores
    // Observaciones adicionales
    val remarks: String?  // Observaciones adicionales, si las hubiera
)
