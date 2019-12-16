package com.bridgelabz.demo.exception.globalexception

import com.bridgelabz.demo.exception.userexception.IncorrectPassword
import com.bridgelabz.demo.exception.userexception.UserAlreadyAvailable
import com.bridgelabz.demo.exception.userexception.UserNotFound
import com.bridgelabz.demo.response.Response
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception

@RestControllerAdvice
class GlobalException {
//    @ExceptionHandler(Exception::class)
//    fun globalException(e:Exception):Response{
//        return Response(200,"Internal Server Error",null)
//    }

    @ExceptionHandler(UserAlreadyAvailable::class)
    fun userAlreadyAvailable(e:UserAlreadyAvailable):Response{
        return Response(200,"EmailId already registered",null)
    }
    @ExceptionHandler(UserNotFound::class)
    fun userAlreadyAvailable(e:UserNotFound):Response{
        return Response(200,"EmailId not registered",null)
    }
    @ExceptionHandler(IncorrectPassword::class)
    fun userAlreadyAvailable(e:IncorrectPassword):Response{
        return Response(200,"Password entered is wrong",null)
    }
}