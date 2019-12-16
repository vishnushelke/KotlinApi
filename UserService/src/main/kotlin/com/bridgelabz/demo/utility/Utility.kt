package com.bridgelabz.demo.utility

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class Utility {
    @Bean
    fun getMapper():ModelMapper{
        return ModelMapper()
    }
    @Bean
    fun getPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}