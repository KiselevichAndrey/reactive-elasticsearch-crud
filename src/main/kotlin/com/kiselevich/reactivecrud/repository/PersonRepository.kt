package com.kiselevich.reactivecrud.repository

import com.kiselevich.reactivecrud.model.Person
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository

interface PersonRepository : ReactiveElasticsearchRepository<Person, String>
