package com.bridgelabz.demo.exception.userexception

class UserNotFound : RuntimeException{
    constructor(message: String) : super(message)
}