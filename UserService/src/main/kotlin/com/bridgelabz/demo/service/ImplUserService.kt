package com.bridgelabz.demo.service

import com.bridgelabz.demo.dto.LoginDto
import com.bridgelabz.demo.dto.RegisterDto
import com.bridgelabz.demo.exception.userexception.IncorrectPassword
import com.bridgelabz.demo.exception.userexception.UserAlreadyAvailable
import com.bridgelabz.demo.exception.userexception.UserNotFound
import com.bridgelabz.demo.model.Customer
import com.bridgelabz.demo.repository.UserRepository
import com.bridgelabz.demo.response.Response
import com.bridgelabz.demo.utility.Utility
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ImplUserService : IUserService {
    @Autowired
    private lateinit var repository: UserRepository

    @Autowired
    private lateinit var mapper : ModelMapper

    @Autowired
    private lateinit var utility : Utility

    override fun registerCustomer(registerDto: RegisterDto): Response {
        if(repository.findAll().stream().anyMatch { i -> i.email == registerDto.email })
            throw UserAlreadyAvailable("User Already available with this email")
        var password : String = utility.getPasswordEncoder().encode(registerDto.password)
        var customer : Customer = mapper.map(registerDto, Customer::class.java)
        customer.password = password
        repository.save(customer)
        return Response(200,"user successfully registered",null)
    }

    override fun loginCustomer(loginDto: LoginDto) : Response{
        var customerOptional : Optional<Customer> = repository.findAll().stream().filter{i->i.email.equals(loginDto.email)}.findAny()
        if(customerOptional==null)
            throw UserNotFound("User not found")
        var customer = customerOptional.get()
        if(!utility.getPasswordEncoder().matches(loginDto.password,customer.password))
            throw IncorrectPassword("Password entered is wrong")
        return Response(200,"Login successfull",null)
    }

    override fun forgetPassword(email: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}