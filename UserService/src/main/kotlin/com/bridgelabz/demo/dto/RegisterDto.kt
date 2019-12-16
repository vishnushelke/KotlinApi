package com.bridgelabz.demo.dto

import lombok.Data

@Data
class RegisterDto {
    var firstname:String = "";
    var lastname:String = "";
    var email:String="";
    var password:String="";
    override fun toString(): String {
        return "RegisterDto(firstname='$firstname', lastname='$lastname', email='$email', password='$password')"
    }


}