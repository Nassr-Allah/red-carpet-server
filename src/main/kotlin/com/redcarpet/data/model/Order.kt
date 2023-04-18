package com.redcarpet.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Order(
    val status: String,
    val totalPrice: Int,
    val isPatternIncluded: Boolean,
    val date: String
)
