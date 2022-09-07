package com.example.jetpackcomponentspractice

sealed class Response<T>(val response : T? = null , val errorMessage : String ?= null){

    class Loading<T> : Response<T>()
    class Success<T>(successResponse : T?) : Response<T>(response = successResponse)
    class Failure<T>(errorMessageString: String?) : Response<T>(errorMessage = errorMessageString)

}
