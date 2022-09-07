package com.example.jetpackcomponentspractice.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomponentspractice.Response
import com.example.jetpackcomponentspractice.models.APIResponseModel
import com.example.jetpackcomponentspractice.repositories.MainActivityRepository
import com.example.jetpackcomponentspractice.room.StudentDAO
import com.example.jetpackcomponentspractice.room.StudentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(val repository: MainActivityRepository , val page : Int) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
           repository.getListOfUsers(page)
        }
    }

    fun getStudents(): LiveData<List<StudentModel>> {
        return repository.getAllStudents()
    }

    fun insertStudent(student: StudentModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertStudent(student)
        }
    }

    fun getListOfUsers() : LiveData<Response<APIResponseModel>?>{
        return repository.usersListLiveData
    }

}

//    var mutableDomain  = MutableLiveData<String>("My domain")
//
////     var initialName  =  model.name
//    private var initialName  =  MutableLiveData<String>("Kunal Chhabra")
////     var initialDomain  =  model.domain
//    private var initialDomain  =  MutableLiveData<String>("Android development")
//
//    var name : LiveData<String> = initialName
//    var domain : LiveData<String> = initialDomain
//
//    fun update(){
////        initialName = "Paramjeet Singh"
//        initialName.value = "Paramjeet Singh"
////        initialDomain  = "Backend development"
//        initialDomain.value = "Backend development"
//    }

