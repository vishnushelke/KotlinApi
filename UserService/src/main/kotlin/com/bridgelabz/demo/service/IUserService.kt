package com.bridgelabz.demo.service

import com.bridgelabz.demo.dto.LoginDto
import com.bridgelabz.demo.dto.RegisterDto
import com.bridgelabz.demo.response.Response

interface IUserService {

    fun registerCustomer(registerDto: RegisterDto) : Response

    fun loginCustomer(loginDto: LoginDto) : Response

    fun forgetPassword(email:String)
}