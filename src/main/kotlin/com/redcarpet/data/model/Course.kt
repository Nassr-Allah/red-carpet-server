package com.redcarpet.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

@Serializable
data class Course(
    @BsonId
    val id: String = ObjectId().toString(),
    val title: String,
    val image: String,
    val price: Int,
    val description: String,
    val type: String,
    val duration: String,
    val category: String
)
