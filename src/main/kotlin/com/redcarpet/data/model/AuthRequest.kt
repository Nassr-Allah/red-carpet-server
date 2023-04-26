package com.redcarpet.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val phoneNumber: String,
    val password: String
)
