package com.conference.room.booking.api.services

import com.conference.room.booking.api.models.Employee
import com.conference.room.booking.api.models.EmployeeRepository
import java.util.*
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class EmployeeService(private val db: EmployeeRepository) {
    fun findEmployees(): List<Employee> = db.findAll().toList()

    fun findEmployeeById(id: Int): Employee? = db.findByIdOrNull(id)

    fun save(account: Employee): Employee = db.save(account)
}
