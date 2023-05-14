package com.redcarpet.data.model

data class PhoneVerificationResponse(
    val status: String,
    val verificationCode: String?
)
