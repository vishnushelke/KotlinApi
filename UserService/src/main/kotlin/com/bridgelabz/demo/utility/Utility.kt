package com.bridgelabz.demo.utility

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.mail.SimpleMailMessage
import org.springframework.stereotype.Component

@Component
class Utility {
    fun createToken(customerId : Int):String{
        var token : String = Jwts.builder().setSubject(customerId.toString()).signWith(SignatureAlgorithm.HS256,"userId").compact();
        return token
    }
    fun getIdFromToken(token : String) : Int{
        var claims : Claims = Jwts.parser().setSigningKey("userId").parseClaimsJws(token).body
        return claims.subject.toInt()
    }
    fun getMessage(token : String) : SimpleMailMessage {
        var message = SimpleMailMessage()
        message.setText("Registration Successful")
        return message
    }
    fun getMessageForgotPassword(token: String) : SimpleMailMessage {
        var message = SimpleMailMessage()
        message.setText("Response to your forgot password\nhttp://localhost:8080/resetpassword/$token")
        return message
    }
}