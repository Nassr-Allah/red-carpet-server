package com.redcarpet.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class CustomDesign(
    @BsonId
    val id: String = ObjectId().toString(),
    val gender: String,
    val age: Int,
    val type: String,
    val material: String,
    val size: String,
    val colors: String,
    val categoryId: String
)