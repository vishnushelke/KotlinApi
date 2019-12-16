package com.bridgelabz.demo.repository

import com.bridgelabz.demo.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<Customer,Int>{
}