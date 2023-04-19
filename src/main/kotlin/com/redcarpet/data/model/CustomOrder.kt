package com.redcarpet.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class CustomOrder(
    @BsonId
    val id: String = ObjectId().toString(),
    val order: Order,
    val clientId: String,
    val budget: Int,
    val deliveryTime: String,
    val isSewingIncluded: Boolean,
    val isPatternIncluded: Boolean,
    val addition: String,
    val attachment: String,
    val customDesign: CustomDesign
)
