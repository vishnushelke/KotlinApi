package com.bridgelabz.demo.response

class Response {
    var statusCode:Int=0;
    var message:String="";
    var data: Object? =null;

    constructor(statusCode: Int, message: String, data: Object?) {
        this.statusCode = statusCode
        this.message = message
        this.data = data
    }
}