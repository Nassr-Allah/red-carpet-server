package com.redcarpet.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Manager(
    @BsonId
    val id: String = ObjectId().toString(),
    val username: String,
    val password: String
)
