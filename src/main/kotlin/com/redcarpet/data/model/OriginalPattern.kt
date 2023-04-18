package com.redcarpet.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class OriginalPattern(
    @BsonId
    val id: String = ObjectId().toString(),
    val image: String,
    val price: Int
)
