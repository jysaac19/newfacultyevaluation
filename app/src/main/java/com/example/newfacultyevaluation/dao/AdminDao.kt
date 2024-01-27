package com.example.newfacultyevaluation.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.newfacultyevaluation.data.model.Admin
import com.example.newfacultyevaluation.data.model.Course
import com.example.newfacultyevaluation.data.model.CourseFaculty
import com.example.newfacultyevaluation.data.model.Faculty
import com.example.newfacultyevaluation.data.model.Student
import com.example.newfacultyevaluation.data.model.User

@Dao
interface AdminDao {

    @Upsert
    suspend fun upsertAdmin(admin: Admin)
    @Delete
    suspend fun deleteAdminByID(admin: Admin)

    @Upsert
    suspend fun upsertCourseFaculty(courseFaculty: CourseFaculty)

    @Upsert
    suspend fun upsertCourse(course: Course)

    @Update
    suspend fun updateUser(user: User)

    @Update
    suspend fun updateFaculty(faculty: Faculty)

    @Update
    suspend fun updateStudent(student: Student)
}