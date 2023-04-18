package com.redcarpet.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Payment(
    @BsonId
    val id: String = ObjectId().toString(),
    val method: String,
    val receipt: String,
    val date: String,
    val clientId: String
)
