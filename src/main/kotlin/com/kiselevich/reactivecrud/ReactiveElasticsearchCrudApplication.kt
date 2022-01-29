package com.kiselevich.reactivecrud

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactiveElasticsearchCrudApplication

fun main(args: Array<String>) {
    runApplication<ReactiveElasticsearchCrudApplication>(*args)
}
