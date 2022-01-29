package com.kiselevich.reactivecrud.response

import com.kiselevich.reactivecrud.model.Person

class PersonResponse(
    val id: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val birthDate: String
) {
    companion object {
        fun fromEntity(person: Person): PersonResponse =
            PersonResponse(
                id = person.id!!.toHexString(),
                firstName = person.firstName,
                lastName = person.lastName,
                gender = person.gender,
                birthDate = person.birthDate
            )
    }
}