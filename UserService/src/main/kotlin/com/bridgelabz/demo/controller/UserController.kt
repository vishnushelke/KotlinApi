package com.bridgelabz.demo.controller

import com.bridgelabz.demo.dto.LoginDto
import com.bridgelabz.demo.dto.RegisterDto
import com.bridgelabz.demo.repository.UserRepository
import com.bridgelabz.demo.response.Response
import com.bridgelabz.demo.service.ImplUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UserController {
    @Autowired
    private lateinit var service : ImplUserService

    @PostMapping
    fun createCustomer(@RequestBody registerDto:RegisterDto):Response{
        return service.registerCustomer(registerDto)
    }
    @PostMapping("/login")
    fun loginCustomer(@RequestBody loginDto:LoginDto):Response{
        println("in login")
        return service.loginCustomer(loginDto)
    }
}