package net.unir.proyecto

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import java.util.*

fun main(args: Array<String>): Unit =
    io.ktor.server.tomcat.EngineMain.main(args)

/**
 * Please note that you can use any other name instead of *module*.
 * Also note that you can have more then one modules in your application.
 * */
@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation) {
        json()
    }

    routing {
        get("/ping") {
            call.respond(mapOf("result" to "ok"))
        }
        post("/auth") {
            call.respond(
                mapOf("token" to UUID.randomUUID().toString())
            )
        }
        post("/session") {
            call.respond(
                mapOf("id" to UUID.randomUUID().toString())
            )
        }
    }
}

