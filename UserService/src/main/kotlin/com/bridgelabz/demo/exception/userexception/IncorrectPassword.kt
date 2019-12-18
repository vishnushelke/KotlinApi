package com.bridgelabz.demo.exception.userexception

class IncorrectPassword : RuntimeException {
    constructor(message : String) : super(message)
}