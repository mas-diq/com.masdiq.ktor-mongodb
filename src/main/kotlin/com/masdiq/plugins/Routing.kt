package com.masdiq.plugins

import com.masdiq.routes.employeeRoutes
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        employeeRoutes()
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
