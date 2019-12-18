package com.bridgelabz.demo.exception.userexception

import java.lang.RuntimeException

class UserAlreadyAvailable : RuntimeException{
    constructor() : super()
}