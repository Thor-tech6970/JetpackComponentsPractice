package com.example.jetpackcomponentspractice.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDAO {

    @Insert
    suspend fun insertStudent(student : StudentModel)

    @Update
    suspend fun updateStudent(student: StudentModel)

    @Delete
    suspend fun deleteStudent(studentModel: StudentModel)

    @Query("SELECT * from students")
    fun getAllStudents() : LiveData<List<StudentModel>>
}