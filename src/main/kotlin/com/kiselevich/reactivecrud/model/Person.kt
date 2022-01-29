package com.kiselevich.reactivecrud.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

data class Person(
    @Id
    val id: ObjectId? = null,
    var firstName: String,
    var lastName: String,
    var gender: String,
    var birthDate: String
)