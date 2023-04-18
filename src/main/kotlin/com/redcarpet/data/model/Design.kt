package com.redcarpet.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Design(
    @BsonId
    val id: String = ObjectId().toString(),
    val name: String,
    val designer: String,
    val price: Int,
    val images: List<String>,
    val sizes: List<String>,
    val colors: List<Int>,
    val description: String,
    val categoryId: String,
    val delivery: Delivery,
    val patternId: String
)
