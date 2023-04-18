package com.redcarpet.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Delivery(
    val places: String,
    val price: Int
)
