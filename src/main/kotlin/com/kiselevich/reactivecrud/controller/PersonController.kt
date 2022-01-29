package com.kiselevich.reactivecrud.controller

import com.kiselevich.reactivecrud.request.PersonRequest
import com.kiselevich.reactivecrud.response.PersonResponse
import com.kiselevich.reactivecrud.service.PersonService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

@RestController
@RequestMapping("/api/person")
class PersonController (
    private val personService: PersonService
) {

    @PostMapping
    fun createPerson(@RequestBody request: PersonRequest): Mono<PersonResponse> {
        return personService.createPerson(request)
            .map { PersonResponse.fromEntity(it) }
    }

    @GetMapping
    fun findAllPersons(): Flux<PersonResponse> {
        return personService.findAll()
            .map { PersonResponse.fromEntity(it) }
            .delayElements(Duration.ofSeconds(2))
    }

    @GetMapping("/{id}")
    fun findPersonById(@PathVariable id: String): Mono<PersonResponse> {
        return personService.findById(id)
            .map { PersonResponse.fromEntity(it) }
    }

    @PutMapping("/{id}")
    fun updatePerson(
        @PathVariable id: String,
        @RequestBody request: PersonRequest
    ): Mono<PersonResponse> {
        return personService.updatePerson(id, request)
            .map { PersonResponse.fromEntity(it) }
    }

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable id: String): Mono<Void> {
        return personService.deleteById(id)
    }
}