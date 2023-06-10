package com.masdiq

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Employee(
    val name: String,
    val surName: String,
    val year: String,
    @BsonId
    var id: String = ObjectId().toString(),
)
