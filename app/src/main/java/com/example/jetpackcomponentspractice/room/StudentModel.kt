package com.example.jetpackcomponentspractice.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentModel(

    val name : String,
    @PrimaryKey(autoGenerate = true)
    val rollNumber : Int,
    val domain : String,
    val hobby : String? = ""

)
