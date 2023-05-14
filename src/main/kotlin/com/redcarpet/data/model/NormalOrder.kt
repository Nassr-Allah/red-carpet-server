package com.redcarpet.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class NormalOrder(
    @BsonId
    val id: String = ObjectId().toString(),
    val order: Order,
    val clientId: String,
    val designId: String,
    val delivery: Delivery,
    val size: String
)
