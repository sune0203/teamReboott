package com.reboott.linguatech

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.ScriptUtils
import javax.sql.DataSource

@SpringBootApplication
class LinguatechApplication {
	
}

fun main(args: Array<String>) {
	runApplication<LinguatechApplication>(*args)
}
