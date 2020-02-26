package com.manga4all.scrapper

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ScrapperApplication

fun main(args: Array<String>) {
	runApplication<ScrapperApplication>(*args)
}
