package com.example.jetpackcomponentspractice.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jetpackcomponentspractice.Response
import com.example.jetpackcomponentspractice.retrofit.RetrofitInterface
import com.example.jetpackcomponentspractice.models.APIResponseModel
import com.example.jetpackcomponentspractice.room.StudentDAO
import com.example.jetpackcomponentspractice.room.StudentModel

class MainActivityRepository(val dao : StudentDAO , val retrofitInterface: RetrofitInterface) {

    private val usersList = MutableLiveData<Response<APIResponseModel>?>()
    val usersListLiveData : LiveData<Response<APIResponseModel>?> = usersList

//    private val usersList = MutableLiveData<APIResponseModel>()
//    val usersListLiveData : LiveData<APIResponseModel> = usersList

    suspend fun insertStudent(studentModel: StudentModel){
        dao.insertStudent(studentModel)
    }

    fun getAllStudents() : LiveData<List<StudentModel>> {
       return dao.getAllStudents()
    }

//    suspend fun getListOfUsers(page : Int){
//        val result = retrofitInterface.getListOfUsers(page)
//        usersList.postValue(result.body())
//    }

//    suspend fun getListOfUsers(page : Int){

//        try {
//            val result : retrofit2.Response<APIResponseModel>? = retrofitInterface.getListOfUsers(page)
//            if (result?.body() != null) {
//                usersList.postValue(result)
//            }
//            else {
//                usersList.postValue(Response.Failure("Error occured. Check API end points"))
//            }

//        }catch (e: Exception){
//            usersList.postValue(Response.Failure("Technical error occured"))
//        }
//    }

    suspend fun getListOfUsers(page : Int){

        try{
            val result = retrofitInterface.getListOfUsers(page)
            if(result?.body()!=null){
                usersList.postValue(Response.Success(result.body()))
            }else{
                usersList.postValue(Response.Failure("Error occured . Check API end points"))
            }

        }catch (e : Exception){
            usersList.postValue(Response.Failure("Technical error occured"))

        }


    }

}

