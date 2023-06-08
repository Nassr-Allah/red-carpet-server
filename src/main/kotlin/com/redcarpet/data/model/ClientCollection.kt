package com.redcarpet.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class ClientCollection(
    @BsonId
    val id: String = ObjectId().toString(),
    val designs: List<String>,
    val patterns: List<String>,
    val clientId: String
)
