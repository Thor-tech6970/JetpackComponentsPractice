package com.example.jetpackcomponentspractice.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomponentspractice.viewModels.MainActivityViewModel
import com.example.jetpackcomponentspractice.models.NameDomainModel
import com.example.jetpackcomponentspractice.R
import com.example.jetpackcomponentspractice.Response
import com.example.jetpackcomponentspractice.retrofit.RetrofitHelper
import com.example.jetpackcomponentspractice.retrofit.RetrofitInterface
import com.example.jetpackcomponentspractice.databinding.ActivityMainBinding
import com.example.jetpackcomponentspractice.repositories.MainActivityRepository
import com.example.jetpackcomponentspractice.room.StudentDAO
import com.example.jetpackcomponentspractice.room.StudentDatabase
import com.example.jetpackcomponentspractice.room.StudentModel
import com.example.jetpackcomponentspractice.viewModelFactories.MainViewModelFactory

class MainActivity : AppCompatActivity() {

//    private lateinit var nameTV : TextView
//    private lateinit var domainTV : TextView
//    private lateinit var updateBT : Button
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var database : StudentDatabase
    private lateinit var retrofitInterface : RetrofitInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)

        database = StudentDatabase.getDatabaseInstance(this)
        val dao : StudentDAO = database.studentDao()

        retrofitInterface = RetrofitHelper.getInstance().create(RetrofitInterface::class.java)

        val repo = MainActivityRepository(dao , retrofitInterface)

        val model = NameDomainModel("Kunal Chhabra" , "Android development" , "https://cloudsek.com/wp-content/uploads/2020/07/Android-main.jpg")

        viewModel = ViewModelProvider(this , MainViewModelFactory(repo , 1)).get(MainActivityViewModel::class.java)
        binding.model = model
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.getStudents().observe(this , Observer {
            binding.studentsTV.text = it.toString()
        })

        viewModel.getListOfUsers().observe(this , Observer {

            when(it){

                is Response.Loading -> {}
                is Response.Success -> {binding.apiResponseTV.text = it.response.toString()}
                is Response.Failure -> {
                    Toast.makeText(this , it.errorMessage , Toast.LENGTH_LONG).show()}
            }


//            binding.apiResponseTV.text = it?..toString()
        })

        binding.updateButton.setOnClickListener(View.OnClickListener {
            val student  = StudentModel("Radhey Chutia" , 19320, "React Native" , "Gaand marana")
            viewModel.insertStudent(student)
        })




//        database = Room.databaseBuilder(applicationContext , StudentDatabase::class.java , "studentDB").build()
//
//        GlobalScope.launch {
//            database.studentDao().insertStudent(StudentModel("Kunal" , 19314 , "Android" , "Gym"))
//            database.studentDao().insertStudent(StudentModel("Paramjeet" , 0, "Backend" , "BasketBall"))
//        }

//        nameTV = findViewById(R.id.name)
//        domainTV = findViewById(R.id.domain)
//        updateBT = findViewById(R.id.updateButton)

//        viewModel.name.observe(this , Observer {
//            nameTV.text = it ;
//        })

//        viewModel.domain.observe(this , Observer {
//            domainTV.text = it
//        })

//        setText()

//        updateBT.setOnClickListener(View.OnClickListener {
//            viewModel.update()
//            setText()
//        })

    }

//    fun setText(){
//
//        nameTV.text = viewModel.initialName
//        domainTV.text = viewModel.initialDomain
//
//    }
}