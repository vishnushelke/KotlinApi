package com.bridgelabz.demo.configuration

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomerConfig {
    @Bean
    fun getMapper() : ModelMapper {
        return ModelMapper()
    }
    @Bean
    fun getPasswordEncoder() : BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}