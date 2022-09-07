package com.example.jetpackcomponentspractice.retrofit

import com.example.jetpackcomponentspractice.models.APIResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

    @GET("/api/users/")
    suspend fun getListOfUsers(@Query("pages") page : Int) : Response<APIResponseModel>?

}