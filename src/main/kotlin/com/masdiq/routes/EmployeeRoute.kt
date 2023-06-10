package com.masdiq.routes

import com.masdiq.Employee
import com.masdiq.data.model.createEmployeeOrUpdateEmployeeForId
import com.masdiq.data.model.deleteEmployeeForId
import com.masdiq.data.model.getEmployeeForId
import com.masdiq.data.request.DeleteEmployeeRequest
import com.masdiq.data.request.EmployeeRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.employeeRoutes() {
    get("/get-employee") {
        val employeeId = call.receive<EmployeeRequest>().id
        val employee = getEmployeeForId(employeeId)
        employee?.let {
            call.respond(
                HttpStatusCode.OK,
                it
            )
        } ?: call.respond(
            HttpStatusCode.BadRequest,
            "There is no employee whit this id"
        )
    }

    post("/create-update-employee") {
        val request = try {
            call.receive<Employee>()
        } catch (e: ContentTransformationException) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        if (createEmployeeOrUpdateEmployeeForId(request)) {
            call.respond(
                HttpStatusCode.OK,
                "Employee successfully created"
            )
        } else {
            call.respond(HttpStatusCode.Conflict)
        }
    }

    post("/delete-employee") {
        val request = try {
            call.receive<DeleteEmployeeRequest>()
        } catch (e: ContentTransformationException) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        if (deleteEmployeeForId(request.id)) {
            call.respond(
                HttpStatusCode.OK,
                "Employee successfully deleted"
            )
        } else {
            call.respond(
                HttpStatusCode.BadRequest,
                "Employee not found"
            )
        }
    }
}