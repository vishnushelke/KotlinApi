package com.bridgelabz.demo.service

import com.bridgelabz.demo.dto.LoginDto
import com.bridgelabz.demo.dto.RegisterDto
import com.bridgelabz.demo.exception.userexception.IncorrectPassword
import com.bridgelabz.demo.exception.userexception.UserAlreadyAvailable
import com.bridgelabz.demo.exception.userexception.UserNotFound
import com.bridgelabz.demo.model.Customer
import com.bridgelabz.demo.repository.UserRepository
import com.bridgelabz.demo.response.Response
import com.bridgelabz.demo.configuration.CustomerConfig
import com.bridgelabz.demo.dto.ResetPasswordDto
import com.bridgelabz.demo.utility.Utility
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class ImplUserService : IUserService {
    @Autowired
    private lateinit var repository : UserRepository

    @Autowired
    private lateinit var mapper : ModelMapper

    @Autowired
    private lateinit var customerConfig : CustomerConfig

    @Autowired
    private lateinit var mailSender : JavaMailSender

    @Autowired
    private lateinit var utility : Utility

    override fun registerCustomer(registerDto: RegisterDto): Response {
        if(repository.findAll().stream().anyMatch { i -> i.email == registerDto.email })
            throw UserAlreadyAvailable()
        var password : String = customerConfig.getPasswordEncoder().encode(registerDto.password)
        var customer : Customer = mapper.map(registerDto, Customer :: class.java)
        customer.password = password
        repository.save(customer)
        var token = utility.createToken(customerId = customer.id)
        var message : SimpleMailMessage = utility.getMessage(token)
        message.setTo(registerDto.email)
        mailSender.send(message)
        return Response(200,"user successfully registered",null)
    }

    override fun loginCustomer(loginDto: LoginDto) : Response{
        var customer = repository.findAll().stream().filter{i->i.email == loginDto.getEmail()}.findAny().orElseThrow{UserNotFound()}
        if(!customerConfig.getPasswordEncoder().matches(loginDto.getPassword(),customer.password))
            throw IncorrectPassword("Password entered is wrong")
        return Response(200,"Login successful",null)
    }

    override fun forgetPassword(email: String) : Response{
        var customer = repository.findAll().stream().filter{i->i.email == email}.findAny().orElseThrow{UserNotFound()}
        var token = utility.createToken(customer.id)
        var message = utility.getMessageForgotPassword(token)
        message.setTo(email)
        mailSender.send(message)
        return Response(200,"Link sent to email for resetting password",null)
    }

    override fun resetPassword(token: String, forgotPasswordDto: ResetPasswordDto): Response {
        var customerId = utility.getIdFromToken(token)
        var customer = repository.findById(customerId).orElseThrow{UserNotFound()}
        var password =  customerConfig.getPasswordEncoder().encode(forgotPasswordDto.password)
        customer.password = password
        return Response(200,"Password Reset success",null)
    }
}