package com.kiselevich.reactivecrud.service

import com.kiselevich.reactivecrud.exception.NotFoundException
import com.kiselevich.reactivecrud.model.Person
import com.kiselevich.reactivecrud.repository.PersonRepository
import com.kiselevich.reactivecrud.request.PersonRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class PersonService(
    private val personRepository: PersonRepository
) {

    fun createPerson(request: PersonRequest): Mono<Person> =
        personRepository.save(
            Person(
                firstName = request.firstName,
                lastName = request.lastName,
                gender = request.gender,
                birthDate = request.birthDate
            )
        )

    fun findById(id: String): Mono<Person> =
        personRepository.findById(id)
            .switchIfEmpty {
                Mono.error(
                    NotFoundException("Person with id $id not found")
                )
            }

    fun findAll(): Flux<Person> =
        personRepository.findAll()

    fun updatePerson(id: String, request: PersonRequest): Mono<Person> =
        findById(id)
            .flatMap {
                personRepository.save(
                    it.apply {
                        firstName = request.firstName
                        lastName = request.lastName
                        gender = request.gender
                        birthDate = request.birthDate
                    }
                )
            }

    fun deleteById(id: String): Mono<Void> =
        findById(id)
            .flatMap(personRepository::delete)
}