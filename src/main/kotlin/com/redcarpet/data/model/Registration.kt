package com.redcarpet.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Registration(
    @BsonId
    val id: String = ObjectId().toString(),
    val date: String,
    val courseId: String,
    val clientId: String
)
