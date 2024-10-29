package com.conference.room.booking.api.controllers

import com.conference.room.booking.api.models.Employee
import com.conference.room.booking.api.services.EmployeeService
import jakarta.validation.Valid
import java.net.URI
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/employees")
class Employees(private val service: EmployeeService) {
    @GetMapping fun getAllEmployees() = service.findEmployees()

    @GetMapping("{id}")
    fun getEmployeeById(@PathVariable id: Int): ResponseEntity<Employee> {
        val employee = service.findEmployeeById(id)

        if (employee != null) {

            return ResponseEntity.created(URI("/${employee.id}")).body(employee)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found")
        }
    }

    @PostMapping
    fun postEmployee(@Valid @RequestBody payload: Employee): ResponseEntity<Employee> {
        val savedEmployee = service.save(payload)

        return ResponseEntity.created(URI("/${savedEmployee.id}")).body(savedEmployee)
    }
}
