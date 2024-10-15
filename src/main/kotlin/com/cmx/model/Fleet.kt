package com.cmx.model

import java.util.UUID

data class Fleet(
    val id: UUID,
    val name: String,
    val company: String,
    val billingInfo: BillingInfo,
    val vehicles: List<Vehicle>,
    val drivers: List<Driver>
)
